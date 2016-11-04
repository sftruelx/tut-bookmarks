package bookmarks.specs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static bookmarks.specs.CustomerSpecs.byAuto;


public class CustomRepositoryImpl <T, ID extends Serializable> 
					extends SimpleJpaRepository<T, ID>  implements CustomRepository<T,ID> {
	
	private final EntityManager entityManager;

	@Autowired
	public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Page<T> findByAuto(T example, Pageable pageable) {
		return findAll(byAuto(entityManager, example),pageable);
	}


}
