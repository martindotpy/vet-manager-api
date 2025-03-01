package com.vluepixel.vetmanager.api.shared.domain.exception;

import static com.vluepixel.vetmanager.api.shared.domain.util.SpanishUtils.translate;

import lombok.Getter;

/**
 * Conflict exception.
 */
@Getter
public final class ConflictException extends ErrorException {
    private String message = "% debe ser único(a)";
    private final int status = 409;

    public ConflictException() {
        this.message = this.message.replace("%", "El recurso");
    }

    public ConflictException(String message) {
        this.message = message;
    }

    public ConflictException(Class<?> entity, String field) {
        String entityName = translate(entity.getSimpleName());
        entityName = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);

        this.message = this.message.replace("%", translate(field) + " de " + entityName);
    }
}
