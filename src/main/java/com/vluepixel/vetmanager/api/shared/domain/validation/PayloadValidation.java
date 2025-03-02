package com.vluepixel.vetmanager.api.shared.domain.validation;

import java.util.List;

import com.vluepixel.vetmanager.api.shared.domain.payload.Payload;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Validation payload.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PayloadValidation implements Validation {
    private final Payload payload;

    public static PayloadValidation of(Payload payload) {
        return new PayloadValidation(payload);
    }

    @Override
    public List<ValidationError> validate() {
        return payload.validate();
    }
}
