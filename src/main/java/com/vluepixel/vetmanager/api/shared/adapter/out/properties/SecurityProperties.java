package com.vluepixel.vetmanager.api.shared.adapter.out.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.vluepixel.vetmanager.api.shared.application.annotation.Properties;
import com.vluepixel.vetmanager.api.shared.domain.config.Route;

import lombok.Getter;
import lombok.Setter;

/**
 * Security properties.
 */
@Getter
@Setter
@Properties
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private boolean allowCredentials;
    private String[] allowedOrigins;
    private String[] allowedHeaders;
    private String[] exposedHeaders;
    private String[] allowedMethods;
    private String[] publicRoutes;
    private Route[] adminRoutes;
    private Route[] noAdminOperationToSelfRoutes;
}