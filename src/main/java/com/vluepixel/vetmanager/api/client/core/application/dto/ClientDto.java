package com.vluepixel.vetmanager.api.client.core.application.dto;

import java.util.List;

import com.vluepixel.vetmanager.api.client.core.domain.enums.IdentificationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Client DTO.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class ClientDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String identification;
    private IdentificationType identificationType;
    private String address;

    private List<String> emails;
    private List<String> phones;
}
