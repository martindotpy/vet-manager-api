package com.vluepixel.vetmanager.api.shared.domain.exception;

import lombok.Getter;

/**
 * Repository exception.
 */
@Getter
public final class RepositoryException extends RuntimeException {
    private final Class<?> entityClass;

    public RepositoryException(Throwable cause, Class<?> entityClass) {
        super(cause);
        this.entityClass = entityClass;
    }
}
