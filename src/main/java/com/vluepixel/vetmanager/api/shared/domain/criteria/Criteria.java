package com.vluepixel.vetmanager.api.shared.domain.criteria;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightYellow;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Criteria class.
 */
@Slf4j
@Getter
@ToString
public sealed class Criteria permits OrderedCriteria {
    private final Collection<Filter> filters;

    protected Criteria(Collection<Filter> filters) {
        List<Filter> cleanFilters = new CopyOnWriteArrayList<>();

        cleanFilters(filters, cleanFilters);

        this.filters = cleanFilters;
    }

    /**
     * Creates a new criteria instance.
     *
     * @param necessaryFilter Necessary filter.
     * @param filters         Filters.
     * @return Criteria instance.
     * @throws NullPointerException If any filter is null.
     */
    public static Criteria of(Filter necessaryFilter, Filter... filters) {
        List<Filter> allFilters = new CopyOnWriteArrayList<>(filters);

        allFilters.add(0, necessaryFilter);

        return new Criteria(allFilters);
    }

    /**
     * Creates a new criteria instance.
     *
     * @param filters Filters.
     * @return Criteria instance.
     * @throws IllegalArgumentException If filters is empty.
     */
    public static Criteria of(Collection<Filter> filters) {
        if (filters.isEmpty()) {
            throw new IllegalArgumentException("Filters cannot be empty");
        }

        return new Criteria(new CopyOnWriteArrayList<>(filters));
    }

    private static void cleanFilters(Collection<Filter> filters, Collection<Filter> cleanFilters) {
        for (Filter filter : filters) {
            Objects.requireNonNull(filter, "Filter cannot be null");

            switch (filter) {
                case ValueFilter valueFilter -> {
                    if (valueFilter.getValue() == null && valueFilter.getFilterOperator() != FilterOperator.IS_NULL) {
                        log.warn("Null value for filter: {} - {}",
                                fgBrightYellow(valueFilter.getField()),
                                fgBrightYellow(valueFilter.getFilterOperator()));
                        continue;
                    }

                    if (valueFilter.getValue() instanceof Object[] array && array.length == 0) {
                        log.warn("Empty array for filter: {} - {}",
                                fgBrightYellow(valueFilter.getField()),
                                fgBrightYellow(valueFilter.getFilterOperator()));
                        continue;
                    }

                    cleanFilters.add(filter);
                }
                case LogicalFilter logicalFilter -> {
                    if (logicalFilter.getFilters().length == 0) {
                        log.warn("Empty logical filter: {}",
                                fgBrightYellow(logicalFilter.getLogicalOperator()));
                        continue;
                    }

                    Collection<Filter> cleanChildrenFilters = new CopyOnWriteArrayList<>();

                    cleanFilters(List.of(logicalFilter.getFilters()), cleanChildrenFilters);

                    cleanFilters.add(new LogicalFilter(logicalFilter.getLogicalOperator(), cleanChildrenFilters));
                }
            }
        }
    }
}
