package bookmarks.specs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import javax.persistence.EntityManager;
import java.io.Serializable;

import static bookmarks.specs.BaseSpecs.byAuto;


public class SampleRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SampleRepository<T, ID> {
    private final EntityManager entityManager;

    public SampleRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public Page<T> findByAuto(T sample, Pageable pageable) {
        return findAll(byAuto(entityManager, sample), pageable);
    }
}
