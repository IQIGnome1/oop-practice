import java.util.ArrayList;

public class SuurimaViiviseLeidja  implements  Kontrollija{

    private  double viiviseSuurus;
    private  String laenutajaNimi;
    private String teoseKirjeldus;


    //konstruktor
    public  SuurimaViiviseLeidja(){

    }

    public void saadaHoiatus() {

        System.out.println(laenutajaNimi + " " + teoseKirjeldus);
    }


    @Override
    public void salvestaViivis(String laenutajaNimi, String teoseKirjeldus, double viiviseSuurus) {

        if(viiviseSuurus > this.viiviseSuurus) {
            this.laenutajaNimi = laenutajaNimi;
            this.teoseKirjeldus = teoseKirjeldus;
            this.viiviseSuurus = viiviseSuurus;
        }

    }
}





