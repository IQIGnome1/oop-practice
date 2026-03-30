public class PirateStation extends Broadcaster implements BroadcastListener {

    //пирает слушает новости бесплатно
    public void listen(String message){
        broadcast(message);
    }

}