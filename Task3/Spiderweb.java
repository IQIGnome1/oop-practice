public class Spiderweb implements Effect {

    private int counter = 0;
    private boolean expired = false;
    private int actionPointReduction;

    // konstruktor
    public Spiderweb(int actionPointReduction) {
        this.actionPointReduction = actionPointReduction;
    }

    public int requiredActionPoints() {
        return 5;
    }

    public void onHit(Dude effectTarget) {

    }

    public void onTurnStart(Dude effectTarget) {

        //сделал так чтобы значение не было отрицательным
        effectTarget.actionPoints = Math.max(0, effectTarget.actionPoints - actionPointReduction);

        counter++;


        if (counter >= 2) {
            expired = true;
        }
    }

    public void onTurnEnd(Dude effectTarget) {

    }

    public boolean isExpired() {
        return expired;
    }
}