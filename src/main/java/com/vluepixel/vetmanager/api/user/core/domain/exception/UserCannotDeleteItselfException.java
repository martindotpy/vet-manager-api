package com.vluepixel.vetmanager.api.user.core.domain.exception;

import com.vluepixel.vetmanager.api.shared.domain.exception.ErrorException;

import lombok.Getter;

/**
 * User cannot delete itself exception.
 */
@Getter
public final class UserCannotDeleteItselfException extends ErrorException {
    private final String message = "El usuario no se puede borrar así mismo";
    private final int status = 403;
}
