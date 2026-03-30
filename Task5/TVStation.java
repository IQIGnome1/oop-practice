import java.util.List;

public class TVStation extends  Broadcaster {

    List<String> newsList; // поле для хранения списка

    //  список новостей
    public TVStation(List<String> news){
        newsList = news;
    }



    public  void sendNews(){

        int index = (int)(Math.random() * newsList.size()); // случайный индекс (новость)
        String news = newsList.get(index); // достаём новость
        broadcast(news); // отправляем всем новость


    }


}
