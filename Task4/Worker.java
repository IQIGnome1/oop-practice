import java.io.IOException;
import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

class Worker implements Runnable {

    private BlockingQueue<String> failinimi;
    private BlockingQueue<Tulemus> tulemus;

    public Worker(BlockingQueue<String> failinimi, BlockingQueue<Tulemus> tulemus) {
        this.failinimi = failinimi;
        this.tulemus = tulemus;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Võtame järjekorrast järgmise faili nime
                String nimi = failinimi.poll();
                if (nimi == null) break; // Ülesanded said otsa

                BigInteger kogusumma = BigInteger.ZERO;
                BigInteger suurimArv = BigInteger.ZERO;

                // Loeme faili arvud ükshaaval
                Scanner scanner = new Scanner(new File(nimi), "UTF-8");
                while (scanner.hasNext()) {
                    BigInteger arv = new BigInteger(scanner.next());
                    kogusumma = kogusumma.add(arv); // Liidame summale
                    suurimArv = suurimArv.max(arv); // Uuendame maksimumi
                }

                // Saadame tulemuse tagasi
                tulemus.put(new Tulemus(kogusumma, suurimArv, nimi));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}