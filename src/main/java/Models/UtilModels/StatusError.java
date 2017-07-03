package Models.UtilModels;

/**
 * Created by henry on 2017-07-01.
 */
public enum StatusError {
    BAD_REQUEST(
            "ERROR, USER HAS SUBMITTED AN INVALID REQUEST",
            400
    ),
    BAD_REQUEST_NAME_PARAM(
            "ERROR, Invalid name parameter in request",
            400
    ),
    BAD_REQUEST_LENGTH_PARAM(
            "ERROR, Invalid length parameter in request",
            400
    ),
    BAD_REQUEST_ALBUM_PARAM(
            "ERROR, Invalid album parameter in request",
            400
    ),
    BAD_REQUEST_SONG_WRITERS_PARAM(
            "ERROR, Invalid song writers parameter in request",
            400
    ),
    BAD_REQUEST_SONG_DOES_NOT_EXIST(
            "ERROR, No song exists with this number/name",
            400
    ),
    INTERNAL_ERROR(
            "ERROR, AN INTERNAL ERROR HAS OCCURRED",
            500
    );

    private final String errorMessage;
    private final Integer errorCode;

    private StatusError(String errorMessage, Integer errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
    public Integer getErrorCode(){
        return errorCode;
    }
}
