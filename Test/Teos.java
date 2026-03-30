public abstract class Teos implements Comparable<Teos> {
    private  String teoseKirjelduse;
    private String   teoseTähis;
    private  String laenutaja;
    private  int päaevadearv;



    public Teos(String teoseTähis, String teoseKirjelduse, String laenutaja, int päaevadearv){
        this.teoseTähis =teoseTähis;
        this.teoseKirjelduse = teoseKirjelduse;
        this.laenutaja = laenutaja;
        this.päaevadearv = päaevadearv;

    }

    public  String getTähis(){
        return  teoseTähis;
    }
    public String getTeoseKirjelduse(){
        return teoseKirjelduse;
    }

    public abstract boolean kasHoidlast();

    public  int laenutusaeg(){
        if(teoseTähis.equals("roheline")){
            return  1;

        }else if(teoseTähis.equals("kollane")){
            return  30;
        }else if (teoseTähis.equals("sinine")){
            return 60;

        }else if(teoseTähis.equals("puudub")){
            return 14;

        }else{

            return  0;
        }
    }


    public  double päevaneViivis(){
        if(teoseTähis.equals("roheline")){
            return  2.0;

        }else if(teoseTähis.equals("kollane")){
            return  0.05;
        }else if (teoseTähis.equals("sinine")){
            return 0.05;

        }else if(teoseTähis.equals("puudub")){
            return 0.15;

        }else{

            return  0;
        }
    }

    public void arvutaViivis(Kontrollija k){
        if(päaevadearv > laenutusaeg()){
            double viivis = (päaevadearv - laenutusaeg()) * päevaneViivis();
            k.salvestaViivis(laenutaja, teoseKirjelduse, viivis);
        }

    }

    @Override
    public int compareTo(Teos teine) {
        return this.teoseKirjelduse.compareTo(teine.teoseKirjelduse);
    }

    @Override
    public String toString() {
        return "Kirjeldus: " + teoseKirjelduse +
                ", tähis: " + teoseTähis +
                ", laenutaja: " + laenutaja +
                ", päevi: " + päaevadearv +
                ", hoidlast: " + kasHoidlast();
    }




}
