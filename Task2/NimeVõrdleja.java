import java.util.Comparator;

public class NimeVõrdleja implements Comparator<String> {

    @Override
    public int compare(String a, String b) {
        return b.compareTo(a); // vastupidine järjekord
    }
}
