package com.vluepixel.vetmanager.api.shared.domain.criteria;

import static com.vluepixel.vetmanager.api.shared.domain.util.CaseConverterUtils.toCamelCase;

import java.util.Objects;

import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

import lombok.Getter;
import lombok.ToString;

/**
 * Value filter.
 */
@Getter
@ToString
public final class ValueFilter extends Filter {
    private final String field;
    private final FilterOperator filterOperator;
    private final Object value;

    protected ValueFilter(String field, FilterOperator operator, Object value) {
        super();
        Objects.requireNonNull(field, "Field is required");
        Objects.requireNonNull(operator, "Operator is required");

        if (value instanceof String stringValue) {
            if (stringValue.isBlank()) {
                value = null;
            } else {
                value = StringUtilsMapper.trimString(stringValue);
            }
        }

        this.field = toCamelCase(field);
        this.filterOperator = operator;
        this.value = value;
    }

    protected ValueFilter(String field, FilterOperator operator) {
        super();
        Objects.requireNonNull(field, "Field is required");
        Objects.requireNonNull(operator, "Operator is required");

        this.field = toCamelCase(field);
        this.filterOperator = operator;
        this.value = null;
    }
}
