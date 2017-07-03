package Controllers;

import Models.Album;
import Models.Song;
import Models.UtilModels.ServiceResponse;
import Services.AlbumService;
import Services.SongService;
import Utils.Constants;
import com.google.gson.Gson;

import static Utils.JsonUtil.json;
import static spark.Spark.*;

public class RESTcontroller {
    private static final String SONGS_BASE = "/songs";
    private static final String ALBUMS_BASE = "/albums";

    private static final String NUMBER_PARAM = ":number";

    private static final String GET_ALL_SONGS = SONGS_BASE;
    private static final String GET_SONG = SONGS_BASE + "/" + NUMBER_PARAM;
    private static final String CREATE_SONG = SONGS_BASE;
    private static final String UPDATE_SONG = SONGS_BASE + "/" + NUMBER_PARAM;
    private static final String DELETE_SONG = SONGS_BASE + "/" + NUMBER_PARAM;

    private static final String GET_ALL_ALBUMS = ALBUMS_BASE;
    private static final String GET_ALBUM = ALBUMS_BASE + "/" + NUMBER_PARAM;
    private static final String CREATE_ALBUM = ALBUMS_BASE;
    private static final String UPDATE_ALBUM = ALBUMS_BASE + "/" + NUMBER_PARAM;
    private static final String DELETE_ALBUM = ALBUMS_BASE + "/" + NUMBER_PARAM;

    private static final Gson gson = new Gson();

    public RESTcontroller(final AlbumService albumService, final SongService songService){

        /*<-------------SONG METHODS------------->*/

        get(GET_ALL_SONGS, (request, response) -> {
            String writers = request.queryParams().contains("writers") ? request.queryParams("writers") : "";
            String album = request.queryParams().contains("album") ? request.queryParams("album") : "";
            ServiceResponse serviceResponse = songService.getAllSongs(writers, album);

            if(serviceResponse.hasError()){
                response.status(serviceResponse.getError().getErrorCode());
                return serviceResponse.getError().getErrorMessage();
            }
            return serviceResponse.getData();
        }, json());

        get(GET_SONG, (request, response) -> {
            ServiceResponse serviceResponse = songService.getSong(request.params(NUMBER_PARAM));
            if(serviceResponse.hasError()){
                response.status(serviceResponse.getError().getErrorCode());
                return serviceResponse.getError().getErrorMessage();
            }
            return serviceResponse.getData().get(0);
        }, json());

        post(CREATE_SONG, (request, response) -> {
            Song song = gson.fromJson(request.body(), Song.class);
            ServiceResponse serviceResponse = songService.createSong(song);
            if(serviceResponse.hasError()){
                response.status(serviceResponse.getError().getErrorCode());
                return serviceResponse.getError().getErrorMessage();
            }
            return serviceResponse.getData().get(0);
        }, json());

        put(UPDATE_SONG, (request, response) -> {
            Song song = gson.fromJson(request.body(), Song.class);
            ServiceResponse serviceResponse = songService.updateSong(request.params(NUMBER_PARAM), song);
            if(serviceResponse.hasError()){
                response.status(serviceResponse.getError().getErrorCode());
                return serviceResponse.getError().getErrorMessage();
            }
            return serviceResponse.getData().get(0);
        }, json());

        delete(DELETE_SONG, (request, response) -> {
            ServiceResponse serviceResponse = songService.deleteSong(request.params(NUMBER_PARAM));
            if(serviceResponse.hasError()){
                response.status(serviceResponse.getError().getErrorCode());
                return serviceResponse.getError().getErrorMessage();
            }
            return serviceResponse.getData().get(0);
        }, json());


        /*<-------------ALBUM METHODS------------->*/

        get(GET_ALL_ALBUMS, (request, response) -> {
            ServiceResponse serviceResponse = albumService.getAllAlbums();
            if(serviceResponse.hasError()){
                response.status(serviceResponse.getError().getErrorCode());
                return serviceResponse.getError().getErrorMessage();
            }
            return serviceResponse.getData().get(0);
        });

        get(GET_ALBUM, (request, response) -> {
            ServiceResponse serviceResponse = albumService.getAlbum(request.params(NUMBER_PARAM));
            if(serviceResponse.hasError()){
                response.status(serviceResponse.getError().getErrorCode());
                return serviceResponse.getError().getErrorMessage();
            }
            return serviceResponse.getData();
        });

        post(CREATE_ALBUM, (request, response) -> {
            Album album = gson.fromJson(request.body(), Album.class);
            ServiceResponse serviceResponse = albumService.createAlbum(album);
            if(serviceResponse.hasError()){
                response.status(serviceResponse.getError().getErrorCode());
                return serviceResponse.getError().getErrorMessage();
            }
            return serviceResponse.getData().get(0);
        });

        after(((request, response) ->
            response.type(Constants.CONTENT_TYPE)
        ));
    }
}
