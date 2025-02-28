package com.vluepixel.vetmanager.api.shared.domain.exception;

import static com.vluepixel.vetmanager.api.shared.domain.util.CaseConverterUtils.toSnakeCase;

import java.util.List;
import java.util.stream.Stream;

import com.vluepixel.vetmanager.api.shared.domain.util.ClassUtils;
import com.vluepixel.vetmanager.api.shared.domain.validation.ValidationError;

import lombok.Getter;

/**
 * Invalid order by field exception.
 */
@Getter
public final class InvalidOrderByException extends ValidationException {
    public InvalidOrderByException(Class<?> clazz) {
        super(List.of(
                new ValidationError(
                        "query.order_by",
                        "Solo los siguientes campos son válidos: " + Stream.of(clazz.getDeclaredFields())
                                .filter(field -> ClassUtils.isPrimitive(field.getType()))
                                .map(field -> toSnakeCase(field.getName()))
                                .filter(
                                        field -> {
                                            return !("deleted".equals(field) ||
                                                    "password".equals(field) ||
                                                    field.contains("url"));
                                        })
                                .reduce((a, b) -> a + ", " + b)
                                .get())));
    }
}
