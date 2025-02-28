package com.vluepixel.vetmanager.api.shared.domain.exception;

import static com.vluepixel.vetmanager.api.shared.domain.util.SpanishUtils.translate;

import lombok.Getter;

/**
 * Not found exception.
 */
@Getter
public final class NotFoundException extends ErrorException {
    private String message = "% no encontrado(a)";
    private final int status = 404;

    public NotFoundException() {
        this.message = this.message.replace("%", "Recurso");
    }

    public NotFoundException(String entity) {
        this.message = this.message.replace("%", translate(entity));
    }

    public NotFoundException(Class<?> entity) {
        this(translate(entity));
    }

    public NotFoundException(String entity, Object id) {
        this.message = this.message.replace("%", translate(entity) + " con id " + id);
    }

    public NotFoundException(Class<?> entity, Object id) {
        this(translate(entity), id);
    }

    public NotFoundException(String entity, String field, Object value) {
        this.message = this.message.replace("%", translate(entity) + " con " + translate(field) + " " + value);
    }

    public NotFoundException(Class<?> entity, String field, Object value) {
        this(translate(entity), translate(field), value);
    }
}
