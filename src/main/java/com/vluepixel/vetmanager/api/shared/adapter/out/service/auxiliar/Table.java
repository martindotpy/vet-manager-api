package com.vluepixel.vetmanager.api.shared.adapter.out.service.auxiliar;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightYellow;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.vluepixel.vetmanager.api.shared.domain.util.ClassUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Table.
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public class Table {
    private final Class<?> entityType;
    private final String[] headers;
    private final List<Object[]> rows = new ArrayList<>();
    private final List<Table> nestedTables = new ArrayList<>();

    private final Set<String> processedTables;

    public Table(Class<?> entityType) {
        this(entityType, new HashSet<>());
    }

    private Table(Class<?> entityType, Set<String> processedTables) {
        this.entityType = entityType;
        this.headers = initializeHeaders();
        this.processedTables = processedTables;
    }

    /**
     * Process data.
     *
     * @param data the data.
     * @throws IllegalArgumentException if an illegal or inappropriate argument is
     *                                  passed.
     * @throws IllegalAccessException   if an illegal access operation is attempted.
     */
    public void process(List<?> data) throws IllegalAccessException {
        processData(data, entityType.getSimpleName());
    }

    private void processData(List<?> rawData, String tableName) throws IllegalAccessException {
        MultiValueMap<String, Object> nestedDataMap = new LinkedMultiValueMap<>();
        List<?> distinctData = rawData.stream().distinct().collect(Collectors.toList());

        Field[] fields = ClassUtils.getAllFields(entityType);
        FieldClassification fieldClassification = classifyFields(fields);

        if (fieldClassification.isSimpleCase())
            processSimpleRows(distinctData, fields, nestedDataMap);
        else
            processComplexRows(distinctData, fields, fieldClassification, nestedDataMap);

        createNestedTables(nestedDataMap);
    }

    private String[] initializeHeaders() {
        return Arrays.stream(ClassUtils.getAllFields(entityType))
                .map(Field::getName)
                .toArray(String[]::new);
    }

    private FieldClassification classifyFields(Field[] fields) {
        FieldClassification classification = new FieldClassification();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            boolean isList = List.class.isAssignableFrom(field.getType());

            classification.updateClassification(i, field, isList);
        }

        return classification;
    }

    private void processSimpleRows(List<?> data, Field[] fields, MultiValueMap<String, Object> nestedData)
            throws IllegalAccessException {
        for (Object item : data) {
            if (item == null)
                continue;

            if (item.getClass() != entityType)
                nestedData.add(item.getClass().getSimpleName(), item);

            rows.add(createRowFromItem(item, fields));
        }
    }

    private Object[] createRowFromItem(Object item, Field[] fields) throws IllegalAccessException {
        Object[] row = new Object[fields.length];

        for (int i = 0; i < fields.length; i++)
            row[i] = getFieldValue(fields[i], item);

        return row;
    }

    private void processComplexRows(List<?> rawData, Field[] fields, FieldClassification classification,
            MultiValueMap<String, Object> nestedData) throws IllegalAccessException {
        for (Object item : rawData) {
            if (item == null)
                continue;

            if (item.getClass() != entityType)
                nestedData.add(item.getClass().getSimpleName(), item);

            Object[] baseRow = createBaseRow(item, fields, classification, nestedData);
            processListFields(item, fields, classification, baseRow, nestedData);
        }
    }

    private Object[] createBaseRow(Object item, Field[] fields, FieldClassification classification,
            MultiValueMap<String, Object> nestedData) throws IllegalAccessException {
        Object[] row = new Object[fields.length];

        for (int index : classification.getNonListPrimitiveIndices())
            row[index] = getFieldValue(fields[index], item);

        for (int index : classification.getNonListNonPrimitiveIndices()) {
            Object value = getFieldValue(fields[index], item);

            if (value != null)
                row[index] = extractAndStoreId(fields[index], value, nestedData);
        }

        if (classification.getListIndices().isEmpty())
            rows.add(row);

        return row;
    }

    private void processListFields(Object item, Field[] fields, FieldClassification classification,
            Object[] baseRow, MultiValueMap<String, Object> nestedData) throws IllegalAccessException {
        for (int index : classification.getListIndices()) {
            Field field = fields[index];
            List<?> listItems = (List<?>) getFieldValue(field, item);
            if (listItems == null)
                continue;

            boolean isPrimitiveList = classification.getPrimitiveIndices().contains(index);

            for (Object listItem : listItems) {
                Object[] newRow = Arrays.copyOf(baseRow, baseRow.length);
                newRow[index] = isPrimitiveList ? listItem : extractAndStoreId(field, listItem, nestedData);
                rows.add(newRow);
            }
        }
    }

    private Object extractAndStoreId(Field field, Object value, MultiValueMap<String, Object> nestedData) {
        try {
            Field idField = ClassUtils.getIdField(value.getClass());
            idField.setAccessible(true);
            nestedData.add(field.getName(), value);

            return idField.get(value);
        } catch (IllegalAccessException e) {
            log.error("Error extracting ID from field {}", field.getName(), e);

            return null;
        }
    }

    private void createNestedTables(MultiValueMap<String, Object> nestedData) {
        nestedData.keySet().forEach(key -> {
            @SuppressWarnings("unchecked")
            List<Object> distinctValues = nestedData.get(key).stream()
                    .filter(Objects::nonNull)
                    .distinct()
                    .sorted((o1, o2) -> {
                        try {
                            Field idField = ClassUtils.getIdField(o1.getClass());
                            idField.setAccessible(true);

                            return ((Comparable<Object>) idField.get(o1)).compareTo(idField.get(o2));
                        } catch (IllegalAccessException e) {
                            log.error("Error sorting nested data", e);

                            return 0;
                        }
                    })
                    .collect(Collectors.toList());

            if (!distinctValues.isEmpty()) {
                try {
                    Class<?> nestedType = distinctValues.get(0).getClass();

                    if (ClassUtils.hasDifferentClasses(distinctValues))
                        nestedType = ClassUtils.getLastParentClass(nestedType);

                    String tableKey = nestedType.getSimpleName();

                    if (processedTables.add(tableKey)) {
                        Table nestedTable = new Table(nestedType, processedTables);
                        nestedTable.processData(distinctValues, key);
                        nestedTables.add(nestedTable);
                    } else {
                        log.debug("Table {} already processed, omitting creation",
                                fgBrightYellow(tableKey));
                    }
                } catch (Exception e) {
                    log.error("Error creating nested table for {}", key, e);
                }
            }
        });
    }

    private Object getFieldValue(Field field, Object target) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(target);
    }

    private Class<?> getListElementType(Field field) {
        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        return (Class<?>) genericType.getActualTypeArguments()[0];
    }

    @Getter
    private class FieldClassification {
        private final List<Integer> listIndices = new ArrayList<>();
        private final List<Integer> nonListIndices = new ArrayList<>();
        private final List<Integer> primitiveIndices = new ArrayList<>();
        private final List<Integer> nonPrimitiveIndices = new ArrayList<>();

        void updateClassification(int index, Field field, boolean isList) {
            if (isList) {
                listIndices.add(index);
                Class<?> elementType = getListElementType(field);
                updatePrimitiveClassification(index, elementType);
            } else {
                nonListIndices.add(index);
                updatePrimitiveClassification(index, field.getType());
            }
        }

        private void updatePrimitiveClassification(int index, Class<?> type) {
            if (ClassUtils.isPrimitive(type))
                primitiveIndices.add(index);
            else
                nonPrimitiveIndices.add(index);

        }

        List<Integer> getNonListPrimitiveIndices() {
            return intersection(primitiveIndices, nonListIndices);
        }

        List<Integer> getNonListNonPrimitiveIndices() {
            return intersection(nonPrimitiveIndices, nonListIndices);
        }

        private List<Integer> intersection(List<Integer> list1, List<Integer> list2) {
            return list1.stream()
                    .filter(list2::contains)
                    .collect(Collectors.toList());
        }

        boolean isSimpleCase() {
            return listIndices.isEmpty() && nonPrimitiveIndices.isEmpty();
        }
    }
}