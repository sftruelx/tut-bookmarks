package bookmarks.entity.dto;

import bookmarks.entity.Album;

/**
 * Created by Larry on 2016/11/1.
 */
public class AlbumDto {

    private Album[] albums;

    public Album[] getAlbums() {
        return albums;
    }

    public void setAlbums(Album[] albums) {
        this.albums = albums;
    }
}
