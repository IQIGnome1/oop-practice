import java.util.ArrayList;
import java.util.List;
abstract  class Dude {
    // valib rünnaku efekti
    abstract Effect chooseEffect();

    // kui palju action pointe taastub
    abstract int getActionPointsRegen();
    int  accuracy;
    int armor;
    int health;
    int actionPoints;

    // aktiivsed efektid tegelasel
    List<Effect> activeEffects = new ArrayList<>();




    public void takeTurn(Dude attackTarget) {
        // Turn start: применяем эффекты, которые висят на ЭТОМ персонаже
        for (Effect effect : activeEffects) {
            effect.onTurnStart(this);
        }
        Effect effect = chooseEffect();
        // kontrollime kas action pointe piisab
        if (actionPoints >= effect.requiredActionPoints()){
            int d20 = (int)(Math.random() * 20) + 1;
            // kontroll kas rünnak tabab
            if (d20 + accuracy >= attackTarget.armor) {
                effect.onHit(attackTarget);
                // lisame efekti  aktiivsete efektide hulka
                attackTarget.activeEffects.add(effect);
                actionPoints -= effect.requiredActionPoints();
            }
        }

        // eemaldame aegunud efektid
        activeEffects.removeIf(e -> e.isExpired());

        for (Effect e : activeEffects) {
            e.onTurnEnd(this);
        }
        actionPoints += getActionPointsRegen();
    }


    // kontroll kas tegelane on elus
    boolean isAlive(){
        if(health <=0){
            return false;
        }
        return true;
    }


}
