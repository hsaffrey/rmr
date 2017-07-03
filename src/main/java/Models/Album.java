package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
    private String number;
    private String name;
    private String releaseDate;
    private List<Song> songs = new ArrayList<>();
    private List<String> producers = new ArrayList<>();

    @JsonProperty("number")
    public String getNumber(){
        return number;
    }

    @JsonProperty("name")
    public String getName(){
        return name;
    }

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

    public void setNumber(String number){
        this.number = number;
    }
    public void setName(String name){
        this.name = name;
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
