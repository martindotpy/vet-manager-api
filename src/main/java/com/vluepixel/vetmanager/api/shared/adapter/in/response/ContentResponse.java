package com.vluepixel.vetmanager.api.shared.adapter.in.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Represents a response.
 */
@Getter
@SuperBuilder
public class ContentResponse<T> {
    private final String message;
    private final T content;
}
