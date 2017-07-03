package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by henry on 2017-07-03.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel implements Comparable<BaseModel>{
    private String number;
    private String name;

    @JsonProperty("number")
    public String getNumber() { return number; }

    @JsonProperty("name")
    public String getName(){
        return name;
    }

    public void setNumber(String number){
        this.number = number;
    }
    public void setName(String name){
        this.name = name;
    }

    public int compareTo(BaseModel comparison) {
        return this.number.compareTo(comparison.getNumber());
    }
}
