package bookmarks.workship.repository;

import bookmarks.workship.entity.BizAlbum;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface BizAlbumDao  extends PagingAndSortingRepository<BizAlbum, Long>, JpaSpecificationExecutor {

    List<BizAlbum> findByDescripeLike(String descripe);
    List<BizAlbum> findAll();




}
