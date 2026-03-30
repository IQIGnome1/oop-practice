import java.util.ArrayList;
import java.util.List;

public class Külastaja {

    private List<String> külastuseKirjeldused;

    private int vanus;

    private double kulud;

    public Külastaja() {
        this(0);
    }

    public Külastaja(int vanus) {
        this.vanus = vanus;
        külastuseKirjeldused = new ArrayList<>();
        kulud = 0.0;
    }

    public int getVanus() {
        return vanus;
    }

    public void lisaKirjeldus(String sone) {
        külastuseKirjeldused.add(sone);
    }

    public List<String> kõikKirjeldused() {
        return külastuseKirjeldused;
    }

    public void lisaKulu(double kulu) {
        kulud += kulu;
    }

    public double kuludeSumma() {
        return kulud;
    }
}