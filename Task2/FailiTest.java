import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FailiTest {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("Palun anna kausta nimi käsureal.");
            return;
        }

        Path tee = Path.of(args[0]);

        if (!Files.isDirectory(tee)) {
            System.out.println("See ei ole kaust.");
            return;
        }

        FailiVaatleja vaatleja = new FailiVaatleja();

        Files.walkFileTree(tee, vaatleja);

        List<String> nimed = vaatleja.getFailiNimed();

        Collections.sort(nimed, new NimeVõrdleja());

        for (String nimi : nimed) {
            System.out.println(nimi);
        }
    }
}