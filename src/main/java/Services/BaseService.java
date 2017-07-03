package Services;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import Utils.Constants;

/**
 * Created by henry on 2017-07-01.
 */
public class BaseService {

    protected static final String SONGS = "songs";
    protected static final String ALBUMS = "albums";
    protected static final String FIELD_NUMBER = "number";
    protected static final String FIELD_ALBUM = "album";
    protected static final MongoClient mongo = getClient();
    protected static final DB db = getDb();

    private static MongoClient getClient(){
        return new MongoClient(Constants.DB_HOST, Constants.DB_PORT);
    }

    private static DB getDb(){
            return  mongo.getDB(Constants.DB_NAME);
    }
}
