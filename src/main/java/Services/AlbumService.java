package Services;

import Models.Album;
import Models.Song;
import Models.UtilModels.ServiceResponse;
import Models.UtilModels.StatusError;
import com.mongodb.MongoException;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 2017-07-01.
 */
public class AlbumService extends BaseService {

    public ServiceResponse getAllAlbums(){
        List<Album> albums = getAlbumCollection().find().toArray();
        return new ServiceResponse(albums);
    }

    public ServiceResponse getAlbum(String albumNumber, boolean songs){
        if(StringUtils.isEmpty(albumNumber)){
            return new ServiceResponse(StatusError.BAD_REQUEST);
        }

        Album album = getAlbumCollection().findOne(DBQuery.is(FIELD_NUMBER, albumNumber));


        if(album == null){
            return new ServiceResponse(StatusError.BAD_REQUEST_SONG_DOES_NOT_EXIST);
        }

        if(songs){
            album.setSongs(getAlbumSongs(album.getName()));
        }

        List<Album> albums = new ArrayList<>();
        albums.add(album);
        return new ServiceResponse(albums);
    }

    public ServiceResponse createAlbum(Album album){
        if(StringUtils.isEmpty(album.getName())){
            return new ServiceResponse(StatusError.BAD_REQUEST_NAME_PARAM);
        }
        if(StringUtils.isEmpty(album.getReleaseDate())){
            return new ServiceResponse(StatusError.BAD_REQUEST_RELEASE_DATE_PARAM);
        }
        try{
            getAlbumCollection().insert(album);
        } catch(MongoException e){
            System.out.println(e.getMessage());
            return new ServiceResponse(StatusError.INTERNAL_ERROR);
        }
        List<Album> albums = new ArrayList<>();
        albums.add(album);
        return new ServiceResponse(albums);
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

    private List<Song> getAlbumSongs(String albumName){
        return getSongCollection().find(DBQuery.is(FIELD_ALBUM, albumName)).toArray();
    }
}
