package com.vluepixel.vetmanager.api.shared.domain.exception;

import lombok.Getter;

/**
 * Cannot reduce product quantity exception.
 */
@Getter
public final class CannotReduceProductQuantityexception extends ErrorException {
    private String message = "No se puede reducir a negativo la cantidad de un producto";
    private final int status = 409;
}
