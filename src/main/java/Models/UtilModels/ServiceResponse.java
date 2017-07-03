package Models.UtilModels;

import java.util.List;

/**
 * Created by henry on 2017-07-01.
 */
public class ServiceResponse {
    private List<?> data;
    private StatusError error;

    public ServiceResponse(List<?> data){
        this.data = data;
        this.error = null;
    }

    public ServiceResponse(StatusError statusError){
        this.data = null;
        this.error = statusError;
    }

    public List<?> getData(){
        return data;
    }
    public StatusError getError(){
        return error;
    }
    public void setData(List<?> d){ this.data = d; }
    public void setError(StatusError e){
        this.error = e;
    }
    public Boolean hasError(){
        return error != null;
    }
}
