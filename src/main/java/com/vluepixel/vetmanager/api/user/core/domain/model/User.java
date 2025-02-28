package com.vluepixel.vetmanager.api.user.core.domain.model;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vluepixel.vetmanager.api.shared.adapter.in.util.RegexConstants;
import com.vluepixel.vetmanager.api.user.core.adapter.out.persistence.converter.ListUserRoleAttributeConverter;
import com.vluepixel.vetmanager.api.user.core.domain.model.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User.
 */
@Entity
@Audited
@Table(name = "`user`", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "uk_user_email")
})
@SQLDelete(sql = "UPDATE `user` SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@Getter
@Builder
@ToString(exclude = "password")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(columnDefinition = "varchar(50)")
    private String firstName;
    @NotBlank
    @Size(max = 50)
    @Column(columnDefinition = "varchar(50)")
    private String lastName;
    @Email
    @NotBlank
    @Size(max = 254)
    @Column(columnDefinition = "varchar(254)")
    private String email;
    @NotBlank
    @Column(columnDefinition = "varchar(255)")
    private String password;
    @Convert(converter = ListUserRoleAttributeConverter.class)
    private List<UserRole> roles;
    @Pattern(regexp = RegexConstants.URL)
    @Column(columnDefinition = "varchar(255)")
    private String profileImageUrl;

    @Builder.Default
    private boolean deleted = false;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
