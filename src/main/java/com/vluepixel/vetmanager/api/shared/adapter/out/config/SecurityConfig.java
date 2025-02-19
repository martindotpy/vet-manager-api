package com.vluepixel.vetmanager.api.shared.adapter.out.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vluepixel.vetmanager.api.auth.core.adapter.in.filter.JwtRequestFilter;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.FailureResponse;
import com.vluepixel.vetmanager.api.shared.adapter.out.properties.SecurityProperties;
import com.vluepixel.vetmanager.api.shared.domain.config.Route;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;
import com.vluepixel.vetmanager.api.user.core.domain.model.enums.UserRole;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class for security.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SecurityProperties securityProperties;
    private final JwtRequestFilter jwtRequestFilter;
    private final ObjectMapper objectMapper;
    @Value("${spring.mvc.servlet.path}")
    private String basePath;

    /**
     * Bean for SecurityFilterChain.
     *
     * <p>
     * This bean configure almost all the security properties of the application
     * provided
     * by Spring Security.
     * </p>
     *
     * <p>
     * In this case, the SecurityFilterChain is configured with the properties
     * defined in the application.properties file.
     * </p>
     *
     * @param httpSecurity The HttpSecurity object
     * @return A new instance of SecurityFilterChain
     * @throws Exception If an error occurs
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .authorizeHttpRequests(
                        authRequest -> {
                            authRequest.requestMatchers(securityProperties.getPublicRoutes()).permitAll();

                            provideRoutes(securityProperties.getNoAdminOperationToSelfRoutes(),
                                    (method, paths) -> authRequest.requestMatchers(method, paths)
                                            .access((authentication, object) -> new AuthorizationDecision(
                                                    isAdminAndNotSelf(authentication, object))));

                            provideRoutes(securityProperties.getAdminRoutes(),
                                    (method, paths) -> authRequest.requestMatchers(method, paths).hasRole("ADMIN"));

                            authRequest.anyRequest().authenticated();
                        })
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .authenticationEntryPoint(this::manageNoAuthorized)
                        .accessDeniedHandler(this::manageNoAuthorized))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Bean for AuthenticationManager.
     *
     * @param authenticationConfiguration The AuthenticationConfiguration object.
     * @return A new instance of AuthenticationManager.
     * @throws Exception If an error occurs
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean for CorsConfigurationSource.
     *
     * @return A new instance of CorsConfigurationSource
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(securityProperties.getAllowedOrigins()));
        config.setAllowedMethods(Arrays.asList(securityProperties.getAllowedMethods()));
        config.setAllowedHeaders(Arrays.asList(securityProperties.getAllowedHeaders()));
        config.setAllowCredentials(securityProperties.isAllowCredentials());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    /**
     * Bean for PasswordEncoder.
     *
     * <p>
     * This bean configure the password encoder of the application provided by
     * Spring Security. In this case, the password encoder is BCryptPasswordEncoder.
     * </p>
     *
     * @return A new instance of PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder;
    }

    /**
     * Manage the no authorized request.
     *
     * @param request  The request.
     * @param response The response.
     * @param e        The exception.
     * @throws JsonProcessingException If an error occurs while processing the JSON.
     * @throws IOException             If input or output error occurs.
     */
    private void manageNoAuthorized(HttpServletRequest request, HttpServletResponse response, RuntimeException e)
            throws JsonProcessingException, IOException {
        response.setStatus(403);
        response.setContentType("application/json");

        response.getWriter().write(objectMapper.writeValueAsString(new FailureResponse("Acceso denegado")));
    }

    /**
     * Provide the routes to the consumer.
     *
     * @param routes   The routes to provide.
     * @param consumer The consumer to provide the routes.
     */
    private void provideRoutes(Route[] routes, BiConsumer<HttpMethod, String[]> consumer) {
        if (routes == null) {
            log.warn("No routes provided");

            return;
        }

        for (Route route : routes) {
            var rawMethods = route.getMethods();
            HttpMethod[] methods = new HttpMethod[rawMethods.length];

            for (int i = 0; i < rawMethods.length; i++) {
                methods[i] = HttpMethod.valueOf(rawMethods[i].toString());
            }

            for (HttpMethod method : methods) {
                consumer.accept(method, route.getRoutes());
            }

            if (!Stream.of(methods).anyMatch(method -> method.equals(HttpMethod.OPTIONS)))
                consumer.accept(HttpMethod.OPTIONS, route.getRoutes());
        }
    }

    /**
     * Check if the user is an admin and is not trying to operate with itself.
     *
     * @param authentication The authentication supplier.
     * @param context        The request authorization context.
     * @return True if the user is an admin and is not trying to operate with
     *         itself. False otherwise.
     */
    private boolean isAdminAndNotSelf(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        Authentication auth = authentication.get();

        if (auth == null) {
            return false;
        }

        if (!(auth.getPrincipal() instanceof User)) {
            return false;
        }

        User user = (User) auth.getPrincipal();

        if (!user.getAuthorities().contains(UserRole.ADMIN))
            return false;

        String id = context.getVariables().get("id");

        if (id == null) {
            return false;
        }

        return !id.equals(user.getId().toString());
    }
}
