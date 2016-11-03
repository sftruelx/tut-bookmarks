package bookmarks.specs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Larry on 2016/11/3.
 */
public class SampleRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new SampleRepositoryFactory(entityManager);
    }

    private static class SampleRepositoryFactory extends JpaRepositoryFactory {

        public SampleRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository( RepositoryInformation information, EntityManager entityManager) {
            return new SampleRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return SampleRepositoryImpl.class;
        }
    }
}
