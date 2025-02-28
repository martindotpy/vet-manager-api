package com.vluepixel.vetmanager.api.shared.domain.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * Get all fields from a class.
     *
     * @param clazz the class.
     * @return the fields.
     */
    public static Field[] getAllFields(Class<?> clazz) {
        List<Class<?>> classes = new LinkedList<>();

        while (clazz != null && clazz != Object.class) {
            classes.add(clazz);

            clazz = clazz.getSuperclass();
        }

        return classes.reversed().stream()
                .flatMap(c -> List.of(c.getDeclaredFields()).stream())
                .toArray(Field[]::new);
    }

    /**
     * Get the id field from a class.
     *
     * @param clazz the class.
     * @return the id field. `null` if not found.
     */
    public static Field getIdField(Class<?> clazz) {
        Field idField = null;

        while (clazz != null && clazz != Object.class) {
            try {
                idField = clazz.getDeclaredField("id");
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }

        return idField;
    }

    public static boolean hasDifferentClasses(Collection<?> collection) {
        if (collection.isEmpty())
            return false;

        Class<?> clazz = collection.iterator().next().getClass();

        for (Object item : collection)
            if (!item.getClass().equals(clazz))
                return true;

        return false;
    }

    public static Class<?> getLastParentClass(Class<?> clazz) {
        while (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class)
            clazz = clazz.getSuperclass();

        return clazz;
    }
}
