package Utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by henry on 2017-07-01.
 */
public class JsonUtil {
    public static String toJson(Object object){
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
