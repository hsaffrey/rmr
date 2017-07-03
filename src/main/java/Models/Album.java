package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
    private String name;
    private String releaseDate;
    private List<Song> songs = new ArrayList<>();
    private List<String> producers = new ArrayList<>();

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
}
