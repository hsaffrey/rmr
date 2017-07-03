package Services;

import Models.Album;
import Models.Song;
import Models.UtilModels.ServiceResponse;
import Models.UtilModels.StatusError;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import spark.utils.StringUtils;

import java.util.List;

/**
 * Created by henry on 2017-07-01.
 */
public class AlbumService extends BaseService {

    public ServiceResponse getAllAlbums(){
        List<Album> albums = getAlbumCollection().find().toArray();
        return new ServiceResponse(albums);
    }

    public ServiceResponse getAlbum(String albumNumber){
        if(StringUtils.isEmpty(albumNumber)){
            return new ServiceResponse(StatusError.BAD_REQUEST);
        }

        List<Album> albums = getAlbumCollection().find(DBQuery.is(FIELD_NUMBER, albumNumber)).toArray();

        if(albums.isEmpty()){
            return new ServiceResponse(StatusError.BAD_REQUEST_SONG_DOES_NOT_EXIST);
        }
        return new ServiceResponse(albums);
    }

    public ServiceResponse createAlbum(Album album){
        return null;
    }

    public ServiceResponse updateAlbum(String number, Album album){
        return null;
    }

    public ServiceResponse deleteAlbum(String number){
        return null;
    }

    private JacksonDBCollection<Album, String> getAlbumCollection(){
        return JacksonDBCollection.wrap(db.getCollection(ALBUMS), Album.class, String.class);
    }
    private JacksonDBCollection<Song, String> getSongCollection(){
        return JacksonDBCollection.wrap(db.getCollection(SONGS), Song.class, String.class);
    }

}
