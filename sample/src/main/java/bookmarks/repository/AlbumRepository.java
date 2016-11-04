package bookmarks.repository;

import bookmarks.sampleentity.Album;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public  interface AlbumRepository extends  PagingAndSortingRepository<Album, Long>, JpaSpecificationExecutor {


}
