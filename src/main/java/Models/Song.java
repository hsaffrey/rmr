package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 2017-07-01.
 */

public class Song extends BaseModel{

    private String length;
    private String album;
    private List<String> songWriters = new ArrayList<>();

    @JsonProperty("length")
    public String getLength(){
        return length;
    }

    @JsonProperty("songWriters")
    public List<String> getSongWriters(){
        return songWriters;
    }

    @JsonProperty("album")
    public String getAlbum(){
        return album;
    }

    public void setLength(String length){
        this.length = length;
    }
    public void setAlbum(String album){
        this.album = album;
    }
    public void setSongWriters(List<String> writers){
        this.songWriters = writers;
    }
}
