package com.vluepixel.vetmanager.api.shared.domain.util;

/**
 * Utility class for string operations.
 */
public class StringUtils {
    /**
     * Returns null if the value is blank.
     *
     * @param value the value to check
     * @return null if the value is blank, the value otherwise
     */
    public static String toNullIfBlank(String value) {
        return value != null && value.isBlank() ? null : value;
    }
}
