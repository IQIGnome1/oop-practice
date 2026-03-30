public class Tv implements BroadcastListener{

    String owner;

    public Tv(String owner) {
        this.owner = owner;
    }

    public void listen(String message){
        System.out.println(owner + ": " + message);
    }


}
