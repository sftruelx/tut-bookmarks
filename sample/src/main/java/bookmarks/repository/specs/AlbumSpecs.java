package bookmarks.repository.specs;

import bookmarks.sampleentity.Album;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

/**
 * Created by Larry on 2016/11/3.
 */
public class AlbumSpecs {
    public static Specification<Album> AlbumFormCondition(Album album){
        return new Specification<Album>() {
            @Override
            public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = newArrayList();
                Predicate albumName = null;
                if (!StringUtils.isEmpty(album.getAlbumName())) {
                    albumName = criteriaBuilder.like(root.get("albumName").as(String.class), "%" + album.getAlbumName() + "%");
                    list.add(albumName);
                }
                Predicate enabled = criteriaBuilder.equal(root.get("enabled").as(Integer.class), 1);
                list.add(enabled);
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        };

    }
}
