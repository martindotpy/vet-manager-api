package com.vluepixel.vetmanager.api.shared.domain.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class utilities.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassUtils {
    /**
     * Check if the type is primitive.
     *
     * @param type the type.
     * @return true if the type is primitive, false otherwise.
     */
    public static boolean isPrimitive(Class<?> type) {
        return type.equals(String.class) ||
                type.equals(Integer.class) ||
                type.equals(Long.class) ||
                type.equals(Double.class) ||
                type.equals(Float.class) ||
                type.equals(Boolean.class) ||
                type.equals(BigDecimal.class) ||
                type.equals(LocalDate.class) ||
                type.equals(LocalDateTime.class) ||
                type.equals(Byte.class) ||
                type.equals(Short.class) ||
                type.equals(Character.class) ||
                type.isPrimitive() ||
                type.isEnum();
    }
}
