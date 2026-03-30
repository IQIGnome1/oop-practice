public class LõbustavKloun implements Lõbustus {

    private Kloun kloun;

    public LõbustavKloun(Kloun kloun) {
        this.kloun = kloun;
    }

    @Override
    public void lõbusta(Külastaja kulastaja) {
        kloun.esine(kulastaja);
    }
}