package bookmarks.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

/**
 * Created by Larry on 2016/11/3.
 */
public class BaseSpecs {
    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T sample) {
        final Class<T> type = (Class<T>) sample.getClass();

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = newArrayList();
                EntityType<T> entity = entityManager.getMetamodel().entity(type);
                for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
                    Object attrValue = getValue(sample, attr);
                    if (attrValue != null) {
                        if (attr.getJavaType() == String.class) {
                            if (!StringUtils.isEmpty(attrValue)) {
                                predicates.add(criteriaBuilder.like(root.get(attribute(entity, attr.getName(), String.class)), pattern((String) attrValue)));
                            }
                        } else {
                            predicates.add(criteriaBuilder.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())), attrValue));
                        }
                    }
                }
                return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(toArray(predicates, Predicate.class));
            }

            private <T> Object getValue(T example, Attribute<T, ?> attr) {
                return ReflectionUtils.getField( (Field) attr.getJavaMember(), example);
            }

            private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entityType, String fieldName, Class<E> fieldClass) {
                return entityType.getDeclaredSingularAttribute(fieldName, fieldClass);
            }
        };
    }

    static private String pattern(String str) {
        return "%" + str + "%";
    }
}
