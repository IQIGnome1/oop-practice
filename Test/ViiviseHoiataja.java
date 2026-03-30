import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViiviseHoiataja  implements  Kontrollija {


    //isednivaljand
    private double viivise;
    private ArrayList<String> laenutajaNimiList;


    //konstruktor
    public ViiviseHoiataja(double viivise) {
        this.viivise = viivise;
        laenutajaNimiList = new ArrayList<>();
    }


    @Override
    public void salvestaViivis(String leanutajaNimi, String teoseKirjeldus, double viiviseSuurus) {

        if (viiviseSuurus > viivise) {
            if (!laenutajaNimiList.contains(leanutajaNimi)) {  //

                laenutajaNimiList.add(leanutajaNimi);

            }
        }



    }

    public List<String> getHoiatatavadLaenutajad() {
        return laenutajaNimiList;
    }


}





