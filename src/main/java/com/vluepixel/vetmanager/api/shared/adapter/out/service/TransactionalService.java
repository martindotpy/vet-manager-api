package com.vluepixel.vetmanager.api.shared.adapter.out.service;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.vluepixel.vetmanager.api.shared.application.port.out.TransactionalPort;
import com.vluepixel.vetmanager.api.shared.application.port.out.auxiliar.TransactionalAuxiliar;
import com.vluepixel.vetmanager.api.shared.domain.exception.RepositoryException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Transactional service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public final class TransactionalService implements TransactionalPort {
    private final PlatformTransactionManager transactionManager;

    @Override
    public <T> T run(Function<TransactionalAuxiliar, T> consumer) {
        log.info("Running transactional");

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        TransactionalAuxiliar auxiliar = new TransactionalAuxiliar();

        try {
            T result = consumer.apply(auxiliar);

            transactionManager.commit(status);
            log.info("Transactional completed");

            return result;
        } catch (Exception e) {
            log.error("Error running transactional");

            if (auxiliar.getEntityClass() == null) {
                log.error("Entity class has not been set", e);

                throw e;
            }

            throw new RepositoryException(e, auxiliar.getEntityClass());
        }
    }

    @Override
    public void run(Consumer<TransactionalAuxiliar> consumer) {
        run(auxiliar -> {
            consumer.accept(auxiliar);

            return null;
        });
    }
}
