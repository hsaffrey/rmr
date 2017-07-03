import Controllers.RESTcontroller;
import Services.AlbumService;
import Services.SongService;
import org.apache.log4j.BasicConfigurator;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        new RESTcontroller(new AlbumService(), new SongService());
    }
}