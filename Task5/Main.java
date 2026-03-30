
import java.util.List;

public class Main {
    public static void main(String[] args) {

        TVStation national = new TVStation(List.of(
                "Kim Jong-un on suur juht!",
                "Rahva suur võit!"
        ));

        TVStation foxNews = new TVStation(List.of(
                "USA valimised",
                "Maailma uudised"
        ));

        PirateStation pirate = new PirateStation();

        Tv kimJongUn = new Tv("Kim Jong-un");
        Tv kimYongNam = new Tv("Kim Yong-nam");
        Tv pakPonChu = new Tv("Pak Pon Chu");

        // PirateStation kuulab mõlemat jaama
        national.subscribe(pirate);
        foxNews.subscribe(pirate);

        // TV tellimused
        foxNews.subscribe(kimJongUn);
        national.subscribe(kimJongUn);

        national.subscribe(kimYongNam);

        pirate.subscribe(pakPonChu);

        // saadame uudised
        national.sendNews();
        foxNews.sendNews();
    }
}