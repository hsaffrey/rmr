package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Album extends BaseModel{

    private String releaseDate;
    private List<Song> songs = new ArrayList<>();
    private List<String> producers = new ArrayList<>();

    @JsonProperty("releaseDate")
    public String getReleaseDate(){
        return releaseDate;
    }

    public List<Song> getSongs(){
        return songs;
    }

    @JsonProperty("producers")
    public List<String> getProducers(){
        return producers;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setProducers(List<String> producers){
        this.producers = producers;
    }
}
