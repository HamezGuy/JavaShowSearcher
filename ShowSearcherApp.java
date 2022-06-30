import java.util.List;

public class ShowSearcherApp {
    public static void main(String[] args) throws Exception {
        IShowLoader loader = null; //new ShowLoader();
        List<IShow> shows = loader.loadShows("data/tv_shows.csv");
        IShowSearcherBackend backend = null; //new ShowSearcherBackend();
        for(IShow show : shows) backend.addShow(show);
        IShowSearcherFrontend frontend = null; //new ShowSearcherFrontend(backend);
        frontend.runCommandLoop();
    }
}
