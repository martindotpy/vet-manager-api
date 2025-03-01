package com.vluepixel.vetmanager.api.shared.domain.exception;

import static com.vluepixel.vetmanager.api.shared.domain.util.SpanishUtils.translate;

import lombok.Getter;

/**
 * Register not instance of subclass exception.
 */
@Getter
public final class RegisterNotInstanceOfSubclassException extends ErrorException {
    private String message = "El registro no es del mismo tipo que %";
    private final int status = 409;

    public RegisterNotInstanceOfSubclassException() {
        this.message = this.message.replace("%", "el recurso ingresado");
    }

    public RegisterNotInstanceOfSubclassException(Class<?> entity) {
        String name = translate(entity);
        name = name.substring(0, 1).toLowerCase() + name.substring(1);

        this.message = this.message.replace("%", name);
    }

    public RegisterNotInstanceOfSubclassException(Class<?> entity, Object id) {
        String name = translate(entity);
        name = name.substring(0, 1).toLowerCase() + name.substring(1);

        this.message = this.message.replace("%", name + " con id " + id);
    }
}
