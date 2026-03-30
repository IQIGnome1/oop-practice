public class Knockdown implements Effect {

    /*onHit — уменьшает actionPoints цели до 0
onTurnStart и onTurnEnd — ничего не делают
isExpired — всегда возвращает true */

    @Override
    public void onHit(Dude effectTarget) {
        effectTarget.actionPoints=0;
    }


    @Override
    public void onTurnStart(Dude effectTarget) {

    }


    @Override
    public void onTurnEnd(Dude effectTarget) {

    }

    @Override
    // ühekordne efekt
    public boolean isExpired() {
        return true;
    }

    @Override
    public int requiredActionPoints() {
        return 5;
    }
}
