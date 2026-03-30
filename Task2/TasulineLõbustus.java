public class TasulineLõbustus implements Lõbustus {

    private double hind;
    private Lõbustus delegaat;

    public TasulineLõbustus(double hind, Lõbustus delegaat) {
        this.hind = hind;
        this.delegaat = delegaat;
    }

    @Override
    public void lõbusta(Külastaja kulastaja) {
        delegaat.lõbusta(kulastaja);
        kulastaja.lisaKulu(hind);
        kulastaja.lisaKirjeldus("tasusin külastuse eest " + hind);
    }
}