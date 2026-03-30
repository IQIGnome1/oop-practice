public class VanuseKontrollija implements Lõbustus {

    private int nõutudVanus;
    private Lõbustus delegaat;

    public VanuseKontrollija(int nõutudVanus, Lõbustus delegaat) {
        this.nõutudVanus = nõutudVanus;
        this.delegaat = delegaat;
    }

    @Override
    public void lõbusta(Külastaja kulastaja) {
        if (kulastaja.getVanus() < nõutudVanus) {
            kulastaja.lisaKirjeldus("külastaja ei läbinud vanusekontrolli");
            return;
        }

        // Delegeeri ilma funktsionaalsust muutmata
        delegaat.lõbusta(kulastaja);
    }
}