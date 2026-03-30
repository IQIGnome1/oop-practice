public class Raamat extends Teos {

    public Raamat(String teoseTähis, String teoseKirjelduse, String laenutaja, int päaevadearv){
        super( teoseTähis,  teoseKirjelduse,  laenutaja, päaevadearv);
    }



    public  boolean kasHoidlast(){
        if(getTähis().equals("kollane") || getTähis().equals("sinine")){
            return true;
        }
        return false;
    }



    @Override
    public String toString() {
        return super.toString()+ ", tegemist on raamatuga";
    }
}
