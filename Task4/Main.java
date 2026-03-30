import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Järjekorrad: failinimed tööks ja tulemused tagasi
        BlockingQueue<String> failid = new ArrayBlockingQueue<>(100);
        BlockingQueue<Tulemus> tulemused = new ArrayBlockingQueue<>(100);

        // Lisame kõik failinimed järjekorda
        for (String fail : args) {
            failid.put(fail);
        }

        // Loome nii palju lõime kui on protsessori tuumasid
        int tuumad = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < tuumad; i++) {
            new Thread(new Worker(failid, tulemused)).start();
        }

        // Muutujad lõpptulemuste jaoks
        BigInteger kogusumma = BigInteger.ZERO;
        BigInteger suurimArv = BigInteger.ZERO;
        String suurimArvFail = "";
        BigInteger vaiksimSumma = null;
        String vaiksimSummaFail = "";

        // Võtame tulemustest nii mitu korda kui faile oli
        for (int i = 0; i < args.length; i++) {
            Tulemus t = tulemused.take();

            // Liidame faili summa kogusummale
            kogusumma = kogusumma.add(t.getKogusumma());

            // Kontrollime kas see fail sisaldab suuremat arvu
            if (t.getSuuremArv().compareTo(suurimArv) > 0) {
                suurimArv = t.getSuuremArv();
                suurimArvFail = t.getNimi();
            }

            // Kontrollime kas see fail on väikseima summaga
            if (vaiksimSumma == null || t.getKogusumma().compareTo(vaiksimSumma) < 0) {
                vaiksimSumma = t.getKogusumma();
                vaiksimSummaFail = t.getNimi();
            }
        }

        // Väljastame tulemused
        System.out.println("Kogusumma: " + kogusumma);
        System.out.println("Suurim arv: " + suurimArv + " failis " + suurimArvFail);
        System.out.println("Vaikseima summaga fail: " + vaiksimSummaFail);
    }
}