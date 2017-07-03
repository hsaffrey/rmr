package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 2017-07-01.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
    private String number;
    private String name;
    private String length;
    private String album;
    private List<String> songWriters = new ArrayList<>();

    @JsonProperty("number")
    public String getNumber() { return number; }

    @JsonProperty("name")
    public String getName(){
        return name;
    }

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

    public void setNumber(String number){
        this.number = number;
    }
    public void setName(String name){
        this.name = name;
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
