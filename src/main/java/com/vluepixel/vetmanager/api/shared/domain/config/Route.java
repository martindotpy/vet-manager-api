package com.vluepixel.vetmanager.api.shared.domain.config;

import lombok.Data;

/**
 * Route class.
 *
 * <p>
 * Utility class to define the routes of the application.
 * </p>
 */
@Data
public final class Route {
    private String[] routes;
    private Method[] methods;

    public enum Method {
        GET, POST, PUT, DELETE;
    }
}
