package com.vluepixel.vetmanager.api.shared.application.port.out;

import java.util.function.Consumer;
import java.util.function.Function;

import com.vluepixel.vetmanager.api.shared.application.port.out.auxiliar.TransactionalAuxiliar;

/**
 * Transactional port.
 */
public interface TransactionalPort {
    /**
     * Transactional.
     *
     * @param <T>      The type to return.
     * @param function function with transaction auxiliar as argument and return
     *                 type.
     * @return The result of running the function
     */
    <T> T run(Function<TransactionalAuxiliar, T> function);

    /**
     * Transactional.
     *
     * @param consumer consumer with the transactional auxiliar.
     */
    void run(Consumer<TransactionalAuxiliar> consumer);
}
