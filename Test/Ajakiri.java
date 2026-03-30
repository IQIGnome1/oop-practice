public class Ajakiri extends  Teos {

    public Ajakiri(String teoseTähis, String teoseKirjelduse, String laenutaja, int päaevadearv){
        super( teoseTähis,  teoseKirjelduse,  laenutaja, päaevadearv);
    }



    public  boolean kasHoidlast(){
        String[] osad = getTeoseKirjelduse().split("/");
        String [] ajaosad = osad[1].split(",");
        int aasta = Integer.parseInt(ajaosad[0]);
        if(aasta<=2000){
            return true;
        }
        return false;
    }



    @Override
    public String toString() {
        return super.toString()+ ", tegemist on ajakirjuga";
    }


}
