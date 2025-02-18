package com.vluepixel.vetmanager.api.shared.application.port.out.auxiliar;

import lombok.Getter;
import lombok.Setter;

/**
 * Transactional auxiliar. Contains the entity class to be used in the
 * transactional port.
 */
@Getter
@Setter
public class TransactionalAuxiliar {
    private Class<?> entityClass;
}
