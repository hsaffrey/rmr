package Services;

import Models.Song;
import Models.UtilModels.ServiceResponse;
import Models.UtilModels.StatusError;
import com.mongodb.MongoException;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongService extends BaseService{
    private static final String SONGS = "songs";
    private static final String FIELD_NUMBER = "number";

    public ServiceResponse getAllSongs(String writers, String album){
        List<Song> allSongs = getSongCollection().find().toArray();

        album = album.replace('+', ' ');
        writers = writers.replace('+', ' ');

        List<String> writersList = Arrays.asList(writers.split(","));
        List<Song> filteredSongs = new ArrayList<>();

        if(writers.isEmpty() && album.isEmpty()){
            return new ServiceResponse(allSongs);
        }

        for(Song song: allSongs){
            if(song.getAlbum().equals(album)){
                filteredSongs.add(song);
                continue;
            }
            if(song.getSongWriters().containsAll(writersList)){
                filteredSongs.add(song);
            }
        }
        return new ServiceResponse(filteredSongs);
    }

    public ServiceResponse getSong(String songNumber){

        if(StringUtils.isEmpty(songNumber)){
            return new ServiceResponse(StatusError.BAD_REQUEST);
        }
        List<Song> song = getSongCollection().find(DBQuery.is(FIELD_NUMBER, songNumber)).toArray();

        if(song.isEmpty()){
            return new ServiceResponse(StatusError.BAD_REQUEST);
        }
        return new ServiceResponse(song);
    }

    public ServiceResponse createSong(Song song){
        if(StringUtils.isEmpty(song.getNumber())){
            return new ServiceResponse(StatusError.BAD_REQUEST_SONG_DOES_NOT_EXIST);
        }
        if(StringUtils.isEmpty(song.getName())){
            return new ServiceResponse(StatusError.BAD_REQUEST_NAME_PARAM);
        }
        if(StringUtils.isEmpty(song.getAlbum())){
            return new ServiceResponse(StatusError.BAD_REQUEST_ALBUM_PARAM);
        }
        if(StringUtils.isEmpty(song.getLength())){
            return new ServiceResponse(StatusError.BAD_REQUEST_LENGTH_PARAM);
        }

        try{
            getSongCollection().insert(song);
        } catch(MongoException e){
            System.out.println(e.getMessage());
            return new ServiceResponse(StatusError.INTERNAL_ERROR);
        }

        List<Song> returnList = new ArrayList<>();
        returnList.add(song);
        return new ServiceResponse(returnList);
    }

    public ServiceResponse updateSong(String songNumber, Song song){

        if(StringUtils.isEmpty(songNumber)){
            return new ServiceResponse(StatusError.BAD_REQUEST_SONG_DOES_NOT_EXIST);
        }

        Song updatedSong = getSongCollection().findOne(DBQuery.is(FIELD_NUMBER, songNumber));

        if(!StringUtils.isEmpty(song.getName())){
            updatedSong.setName(song.getName());
        }
        if(!StringUtils.isEmpty(song.getAlbum())){
            updatedSong.setAlbum(song.getAlbum());
        }
        if(!StringUtils.isEmpty(song.getLength())){
            updatedSong.setLength(song.getLength());
        }
        if(!StringUtils.isEmpty(song.getSongWriters())){
            updatedSong.setSongWriters(song.getSongWriters());
        }

        try{
            getSongCollection().update(DBQuery.is(FIELD_NUMBER, songNumber), updatedSong);
        }catch(MongoException e) {
            System.out.println(e.getMessage());
            return new ServiceResponse(StatusError.INTERNAL_ERROR);
        }

        List<Song> songs = new ArrayList<>();
        songs.add(updatedSong);
        return new ServiceResponse(songs);
    }

    public ServiceResponse deleteSong(String songNumber){
        List<Song> exists = getSongCollection().find(DBQuery.is(FIELD_NUMBER, songNumber)).toArray();

        if(exists.isEmpty()){
            return new ServiceResponse(StatusError.BAD_REQUEST_SONG_DOES_NOT_EXIST);
        }

        try{
            getSongCollection().remove(DBQuery.is(FIELD_NUMBER, songNumber));
        }catch(MongoException e) {
            System.out.println(e.getMessage());
            return new ServiceResponse(StatusError.INTERNAL_ERROR);
        }
        return new ServiceResponse(exists);
    }

    private JacksonDBCollection<Song, String> getSongCollection(){
        return JacksonDBCollection.wrap(db.getCollection(SONGS), Song.class, String.class);
    }
}
