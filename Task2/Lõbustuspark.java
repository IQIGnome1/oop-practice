import java.util.List;

public class Lõbustuspark {

    private List<Lõbustus> lõbustused;

    public Lõbustuspark(List<Lõbustus> lõbustused) {
        this.lõbustused = lõbustused;
    }

    public void alustaSeiklust(Külastaja kulastaja) {

        System.out.println("alustan seiklust");

        for (Lõbustus l : lõbustused) {
            l.lõbusta(kulastaja);
        }
        //Если метод не static —
        //он вызывается через объект.
        for (String kirjeldus : kulastaja.kõikKirjeldused()) {
            System.out.println(kirjeldus);
        }

        System.out.println("kulude summa: " + kulastaja.kuludeSumma());
    }
}