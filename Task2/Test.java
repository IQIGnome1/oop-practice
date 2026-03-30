import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        // Looge isend Vaaterattast
        Vaateratas vaateratas = new Vaateratas();

        // Looge isend Lasketiirust
        Lasketiir lasketiir = new Lasketiir();

        // Looge Kloun
        //Kloun kloun = new Kloun("Оно");
        Kloun kloun = new Kloun("Bozo");

        // Mähi kloun adapterisse
        LõbustavKloun kloun1 = new LõbustavKloun(kloun);


        Lõbustus tasulineVaateratas = new TasulineLõbustus(2.25, vaateratas);


        Lõbustus tasulineLasketiir = new TasulineLõbustus(1.5, lasketiir);
        Lõbustus vanuseKontrolligaLasketiir = new VanuseKontrollija(10, tasulineLasketiir);

        /*
         * Praegu on objektide järjekord järgmine:
         * VanuseKontrollija -> TasulineLõbustus -> Lasketiir
         *
         * See tähendab, et kui külastaja ei läbi vanusekontrolli,
         * siis delegaati ei kutsuta ja tasu ei lisata.
         *
         * Kui muuta järjekord vastupidiseks:
         * TasulineLõbustus(1.5, new VanuseKontrollija(10, lasketiir)),
         * siis maksaks külastaja ka siis, kui ta ei pääse lasketiiru.
         */

        // Looge list, kus on kõik lõbustused
        List<Lõbustus> lõbustused = new ArrayList<>();
        lõbustused.add(tasulineVaateratas);
        lõbustused.add(vanuseKontrolligaLasketiir);
        lõbustused.add(kloun1);   // ← nüüd lisatud

        // Looge Lõbustuspark
        Lõbustuspark park = new Lõbustuspark(lõbustused);

        // Kaks külastajat: üks 9-aastane ja teine 11-aastane
        Külastaja kulastaja9 = new Külastaja(9);
        Külastaja kulastaja11 = new Külastaja(11);

        //seiklema
        System.out.println("\n--- 9-aastane külastaja ---");
        park.alustaSeiklust(kulastaja9);

        System.out.println("\n--- 11-aastane külastaja ---");
        park.alustaSeiklust(kulastaja11);
    }
}