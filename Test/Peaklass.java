import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Peaklass {

    public static List<Teos> loeTeosed(String failiNimi) throws Exception {
        Scanner scanner = new Scanner(new File(failiNimi), "UTF-8");
        List<Teos> teosed = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String rida = scanner.nextLine();
            String[] osad = rida.split("; ");

            String teoseKirjeldus = osad[0];
            String teoseTahis = osad[1];
            String laenutaja = osad[2];
            int paevadeArv = Integer.parseInt(osad[3]);

            if (teoseKirjeldus.contains("/")) {
                teosed.add(new Ajakiri(teoseTahis, teoseKirjeldus, laenutaja, paevadeArv));
            } else {
                teosed.add(new Raamat(teoseTahis, teoseKirjeldus, laenutaja, paevadeArv));
            }
        }

        scanner.close();
        return teosed;
    }

    public static void main(String[] args) throws Exception {
        List<Teos> teosed = loeTeosed("laenutus.txt");

        Collections.sort(teosed);

        System.out.println("Sorteeritud teosed:");
        for (Teos teos : teosed) {
            System.out.println(teos);
        }

        ViiviseHoiataja hoiataja = new ViiviseHoiataja(0.2);
        SuurimaViiviseLeidja leidja = new SuurimaViiviseLeidja();

        for (Teos teos : teosed) {
            teos.arvutaViivis(hoiataja);
        }

        System.out.println("\nHoiatatavad laenutajad:");
        for (String nimi : hoiataja.getHoiatatavadLaenutajad()) {
            System.out.println(nimi);
        }

        for (Teos teos : teosed) {
            teos.arvutaViivis(leidja);
        }

        System.out.println("\nSuurima viivise hoiatus:");
        leidja.saadaHoiatus();
    }
}