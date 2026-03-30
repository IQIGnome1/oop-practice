import java.util.ArrayList;
import java.util.List;

public class Broadcaster {

    List<BroadcastListener> listeners = new ArrayList<>();

    //Добовляет подписчиков
    public  void subscribe(BroadcastListener listener){

        listeners.add(listener);

    }


    //отправляет новости
    public void  broadcast(String message) {
        for (int i = 0; i <listeners.size() ; i++) {
            listeners.get(i).listen(message);

        }

    }


}
