package bookmarks.repository;

import bookmarks.entity.Album;
import bookmarks.specs.SampleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public  interface AlbumRepository extends  PagingAndSortingRepository<Album, Long>, JpaSpecificationExecutor {


}
