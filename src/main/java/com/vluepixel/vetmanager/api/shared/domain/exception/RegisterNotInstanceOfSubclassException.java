package com.vluepixel.vetmanager.api.shared.domain.exception;

import static com.vluepixel.vetmanager.api.shared.domain.util.SpanishUtils.getName;

import lombok.Getter;

/**
 * Register not instance of subclass exception.
 */
@Getter
public final class RegisterNotInstanceOfSubclassException extends ErrorException {
    private String message = "% no pertenece al registro";
    private final int status = 409;

    public RegisterNotInstanceOfSubclassException() {
        this.message = this.message.replace("%", "El recurso");
    }

    public RegisterNotInstanceOfSubclassException(Class<?> entity) {
        this.message = this.message.replace("%", getName(entity));
    }
}
