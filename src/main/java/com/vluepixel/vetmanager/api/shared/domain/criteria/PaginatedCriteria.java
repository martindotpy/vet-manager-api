package com.vluepixel.vetmanager.api.shared.domain.criteria;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Getter;
import lombok.ToString;

/**
 * Paginated criteria class.
 */
@Getter
@ToString(callSuper = true)
public final class PaginatedCriteria extends OrderedCriteria {
    private final int page;
    private final int size;

    private PaginatedCriteria(int page, int size, Order order, Collection<Filter> filters) {
        super(order, filters);
        this.page = page;
        this.size = size;
    }

    /**
     * Creates a new paginated criteria instance.
     *
     * @param page            Page.
     * @param size            Size.
     * @param order           Order.
     * @param necessaryFilter Necessary filter.
     * @param filters         Filters.
     * @return paginated criteria instance.
     * @throws IllegalArgumentException If page or size is less than 1.
     * @throws NullPointerException     If order or any filter is null.
     */
    public static PaginatedCriteria of(int page, int size, Order order, Filter necessaryFilter, Filter... filters) {
        if (page < 1)
            throw new IllegalArgumentException("Page must be greater than 1");

        if (size < 1)
            throw new IllegalArgumentException("Size must be greater than 1");

        Objects.requireNonNull(order, "Order must not be null");

        var allFilters = new CopyOnWriteArrayList<>(filters);
        allFilters.add(0, necessaryFilter);

        for (var filter : allFilters) {
            Objects.requireNonNull(filter, "Filter must not be null");
        }

        return new PaginatedCriteria(page, size, order, allFilters);
    }

    /**
     * Creates a new paginated criteria instance.
     *
     * @param page    Page.
     * @param size    Size.
     * @param order   Order.
     * @param filters Filters.
     * @return paginated criteria instance.
     * @throws IllegalArgumentException If page or size is less than 1.
     * @throws NullPointerException     If order or any filter is null.
     */
    public static PaginatedCriteria of(int page, int size, Order order, Collection<Filter> filters) {
        if (page < 1)
            throw new IllegalArgumentException("Page must be greater than 1");

        if (size < 1)
            throw new IllegalArgumentException("Size must be greater than 1");

        Objects.requireNonNull(order, "Order must not be null");

        for (var filter : filters) {
            Objects.requireNonNull(filter, "Filter must not be null");
        }

        return new PaginatedCriteria(page, size, order, filters);
    }
}
