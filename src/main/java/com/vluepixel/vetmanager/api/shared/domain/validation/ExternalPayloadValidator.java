package com.vluepixel.vetmanager.api.shared.domain.validation;

import java.util.List;

import com.vluepixel.vetmanager.api.shared.domain.payload.Payload;

/**
 * Represents a external payload validator.
 */
public interface ExternalPayloadValidator {
    /**
     * Validates a payload.
     *
     * @param payload The payload to be validated.
     * @return A list of validation errors
     */
    List<ValidationError> validate(Payload payload);
}
