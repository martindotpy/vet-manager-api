package com.vluepixel.vetmanager.api.shared.adapter.out.persistence;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightRed;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightYellow;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.hibernate.query.sqm.PathElementException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vluepixel.vetmanager.api.shared.domain.criteria.Criteria;
import com.vluepixel.vetmanager.api.shared.domain.criteria.Filter;
import com.vluepixel.vetmanager.api.shared.domain.criteria.LogicalFilter;
import com.vluepixel.vetmanager.api.shared.domain.criteria.OrderType;
import com.vluepixel.vetmanager.api.shared.domain.criteria.OrderedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.criteria.ValueFilter;
import com.vluepixel.vetmanager.api.shared.domain.exception.InvalidOrderByException;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.exception.RepositoryException;
import com.vluepixel.vetmanager.api.shared.domain.query.FieldUpdate;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

/**
 * Criteria entity persistence adapter.
 */
@Slf4j
public abstract class CriteriaEntityPersistenceAdapter<E, ID, R extends JpaRepository<E, ID>>
        extends EntityPersistenceAdapter<E, ID, R> {

    public CriteriaEntityPersistenceAdapter(R repository) {
        super(repository);
    }

    public E findBy(Criteria criteria) {
        try {
            return entityManager.createQuery(createQuery(criteria, entityClass, true)).getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException(entityClass);
        } catch (Exception e) {
            throw new RepositoryException(e, entityClass);
        }
    }

    public List<E> findAllBy(OrderedCriteria orderedCriteria) {
        try {
            return entityManager.createQuery(createQuery(orderedCriteria, true)).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e, entityClass);
        }
    }

    public Long countBy(Criteria criteria) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            Root<E> root = countQuery.from(entityClass);
            countQuery.select(criteriaBuilder.countDistinct(root));
            applyCriteria(criteria, criteriaBuilder, countQuery, root, true);

            return entityManager.createQuery(countQuery).getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(e, entityClass);
        }
    }

    public Paginated<E> findPaginatedBy(PaginatedCriteria criteria) {
        try {
            long totalElements = countBy(criteria);
            int page = criteria.getPage();
            int size = criteria.getSize();
            int totalPages = (int) Math.ceil((double) totalElements / size);

            List<E> entities = entityManager.createQuery(createQuery(criteria, false))
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();

            return Paginated.<E>builder()
                    .content(entities)
                    .page(page)
                    .size(size)
                    .totalElements(totalElements)
                    .totalPages(totalPages)
                    .build();
        } catch (Exception e) {
            log.error("Error finding paginated entities");

            throw new RepositoryException(e, entityClass);
        }
    }

    public Integer updateBy(Criteria criteria, FieldUpdate necessaryFieldUpdate,
            FieldUpdate... fieldUpdates) {
        Objects.requireNonNull(necessaryFieldUpdate, "Necessary field update is required");

        for (FieldUpdate fieldUpdate : fieldUpdates) {
            Objects.requireNonNull(fieldUpdate, "Field update is required");
        }

        FieldUpdate[] allFieldUpdates = new FieldUpdate[fieldUpdates.length + 1];
        allFieldUpdates[0] = necessaryFieldUpdate;
        System.arraycopy(fieldUpdates, 0, allFieldUpdates, 1, fieldUpdates.length);

        try {
            CriteriaUpdate<E> update = buildUpdateQuery(criteria, allFieldUpdates);
            int updatedCount = entityManager.createQuery(update).executeUpdate();
            log.debug("Updated {} entities", updatedCount);

            return updatedCount;
        } catch (Exception e) {
            throw new RepositoryException(e, entityClass);
        }
    }

    private <T> CriteriaQuery<T> createQuery(Criteria criteria, Class<T> resultType, boolean echo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(resultType);
        Root<E> root = query.from(entityClass);
        applyCriteria(criteria, cb, query, root, echo);
        return query;
    }

    private CriteriaQuery<E> createQuery(OrderedCriteria criteria, boolean echo) {
        CriteriaQuery<E> query = createQuery(criteria, entityClass, echo);
        applyOrder(criteria, query);
        return query;
    }

    private <T> void applyCriteria(Criteria criteria, CriteriaBuilder cb, CriteriaQuery<T> query, Root<E> root,
            boolean echo) {
        List<Predicate> predicates = buildPredicates(criteria.getFilters(), cb, root, echo);
        query.where(predicates.toArray(Predicate[]::new));
    }

    private List<Predicate> buildPredicates(Collection<Filter> filters, CriteriaBuilder cb, Root<E> root,
            boolean echo) {
        List<Predicate> predicates = new CopyOnWriteArrayList<>();
        for (Filter filter : filters) {
            buildPredicate(filter, cb, root, predicates, echo);
        }
        return predicates;
    }

    private void buildPredicate(Filter filter, CriteriaBuilder cb, Root<E> root, List<Predicate> predicates,
            boolean echo) {
        switch (filter) {
            case LogicalFilter lf -> handleLogicalFilter(lf, cb, root, predicates, echo);
            case ValueFilter vf -> handleValueFilter(vf, cb, root, predicates);
        }
    }

    private void handleLogicalFilter(
            LogicalFilter filter,
            CriteriaBuilder cb,
            Root<E> root,
            List<Predicate> predicates,
            boolean echo) {
        List<Predicate> logicalPredicates = new CopyOnWriteArrayList<>();
        Arrays.stream(filter.getFilters()).forEach(f -> buildPredicate(f, cb, root, logicalPredicates, echo));

        if (echo)
            log.debug("Logical filter: {} - {}",
                    fgBrightYellow(filter.getLogicalOperator()),
                    fgBrightRed(logicalPredicates.size()));

        Predicate combined = switch (filter.getLogicalOperator()) {
            case AND -> cb.and(logicalPredicates.toArray(Predicate[]::new));
            case OR -> cb.or(logicalPredicates.toArray(Predicate[]::new));
            case NOT -> cb.not(cb.and(logicalPredicates.toArray(Predicate[]::new)));
        };

        predicates.add(combined);
    }

    private void handleValueFilter(ValueFilter filter, CriteriaBuilder cb, Root<E> root, List<Predicate> predicates) {
        Path<?> path = resolvePath(root, filter.getField());
        predicates.add(createPredicate(filter, cb, path, root));
    }

    private boolean isElementCollection(Path<?> path, String attribute) {
        String[] splittedAttribute = attribute.split("\\.");
        String lastAttribute = splittedAttribute[splittedAttribute.length - 1];

        Path<?> parent = path.getParentPath();
        Class<?> parentClass = parent != null ? parent.getJavaType() : entityClass;

        return Stream.of(parentClass.getDeclaredFields())
                .filter(f -> lastAttribute.equals(f.getName()))
                .anyMatch(f -> f.isAnnotationPresent(ElementCollection.class));
    }

    private Join<Object, String> resolveJoin(Root<E> root, String attribute) {
        String[] splittedAttribute = attribute.split("\\.");
        Join<Object, String> join = null;

        for (String attr : splittedAttribute) {
            if (join == null) {
                join = root.join(attr);
                continue;
            }

            join = join.join(attr);
        }

        return join;
    }

    private Predicate createPredicate(ValueFilter filter, CriteriaBuilder cb, Path<?> path, Root<E> root) {
        Join<Object, String> join = null;

        if (isElementCollection(path, filter.getField())) {
            join = resolveJoin(root, filter.getField());
        }

        return switch (filter.getFilterOperator()) {
            case LIKE -> cb.like(join == null ? path.as(String.class) : join, "%" + filter.getValue() + "%");
            case EQUALS -> cb.equal(path, filter.getValue());
            case IN -> path.in(convertToCollection(filter.getValue()));
            case IS_NULL -> cb.isNull(path);
            case GREATER_THAN -> compare(cb, path, filter.getValue(), CompareOperator.GT);
            case GREATER_THAN_OR_EQUAL -> compare(cb, path, filter.getValue(), CompareOperator.GE);
            case LESS_THAN -> compare(cb, path, filter.getValue(), CompareOperator.LT);
            case LESS_THAN_OR_EQUAL -> compare(cb, path, filter.getValue(), CompareOperator.LE);
        };
    }

    @SuppressWarnings("unchecked")
    private Predicate compare(CriteriaBuilder cb, Path<?> path, Object value, CompareOperator operator) {
        Comparable<Object> comparableValue = (Comparable<Object>) value;
        return switch (operator) {
            case GT -> cb.greaterThan(path.as(comparableValue.getClass()), comparableValue);
            case GE -> cb.greaterThanOrEqualTo(path.as(comparableValue.getClass()), comparableValue);
            case LT -> cb.lessThan(path.as(comparableValue.getClass()), comparableValue);
            case LE -> cb.lessThanOrEqualTo(path.as(comparableValue.getClass()), comparableValue);
        };
    }

    private List<?> convertToCollection(Object value) {
        if (value instanceof Iterable<?> iterable) {
            return StreamSupport.stream(iterable.spliterator(), false).toList();
        } else if (value instanceof Object[] array) {
            return Arrays.asList(array);
        }
        throw new IllegalArgumentException("Invalid value type for IN operator: " + value.getClass());
    }

    private void applyOrder(OrderedCriteria criteria, CriteriaQuery<E> query) {
        if (criteria.getOrder().getType() == OrderType.NONE) {
            log.debug("Skipping order: {}", fgBrightRed(criteria.getOrder()));
            return;
        }

        // Get the existing root from the query instead of creating a new one
        @SuppressWarnings("unchecked")
        Root<E> root = (Root<E>) query.getRoots().iterator().next();
        Path<?> path;

        try {
            path = resolvePath(root, criteria.getOrder().getField());
        } catch (RepositoryException e) {
            if (e.getCause() instanceof PathElementException) {
                throw new InvalidOrderByException(entityClass);
            }

            throw e;
        }

        applyOrderDirection(criteria, query, path);
    }

    private void applyOrderDirection(OrderedCriteria criteria, CriteriaQuery<E> query, Path<?> path) {
        switch (criteria.getOrder().getType()) {
            case ASC -> query.orderBy(entityManager.getCriteriaBuilder().asc(path));
            case DESC -> query.orderBy(entityManager.getCriteriaBuilder().desc(path));
            case NONE -> {
                // Do nothing
                log.debug("Skipping order: {}", fgBrightYellow(criteria.getOrder()));
            }
        }
    }

    private CriteriaUpdate<E> buildUpdateQuery(Criteria criteria, FieldUpdate[] fieldUpdates) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<E> update = cb.createCriteriaUpdate(entityClass);
        Root<E> root = update.from(entityClass);

        applyFieldUpdates(cb, update, root, fieldUpdates);
        applyCriteria(criteria, cb, update, root, true);

        return update;
    }

    private void applyCriteria(Criteria criteria, CriteriaBuilder cb, CriteriaUpdate<E> update, Root<E> root,
            boolean echo) {
        List<Predicate> predicates = buildPredicates(criteria.getFilters(), cb, root, echo);
        update.where(predicates.toArray(Predicate[]::new));
    }

    private enum CompareOperator {
        GT, GE, LT, LE
    }
}