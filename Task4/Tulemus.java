import java.math.BigInteger;

public class Tulemus {

    private BigInteger kogusumma;
    private BigInteger suuremArv;
    private String failinimi;

    public String getNimi() {
        return failinimi;
    }

    public BigInteger getKogusumma() {
        return kogusumma;
    }

    public BigInteger getSuuremArv() {
        return suuremArv;
    }


    public Tulemus(BigInteger kogusumma, BigInteger suuremArvu, String failinimi){
        this.kogusumma = kogusumma;
        this.suuremArv = suuremArvu;
        this.failinimi = failinimi;

    }
}