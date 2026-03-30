public  class Firebolt implements Effect {

    private int damage;
    private boolean expired = false;

    // konstruktor
    public Firebolt(int damage) {
        this.damage = damage;
    }

    public void onHit(Dude effectTarget) {
        // efekt rakendub hiljem
    }

    public void onTurnEnd(Dude effectTarget) {
        effectTarget.health -= damage;
        expired = true;
    }

    public void onTurnStart(Dude effectTarget) {

    }

    public boolean isExpired() {
        return expired;
    }


    public int requiredActionPoints() {
        return 5; // или любое число
    }
}