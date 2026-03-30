public class WeaponAttack implements Effect {

    private int  damage;
    WeaponAttack(int damage){
        this.damage = damage;

    }
    @Override
    public  void onHit(Dude effectTarget){
        // vähendab health väärtust
        effectTarget.health -= damage;

    }

    @Override
    public void onTurnStart(Dude effectTarget){

    }
    @Override
    public  void onTurnEnd(Dude effectTarget){

    }

    @Override
    public int requiredActionPoints() {return 5;}
    @Override
    public boolean isExpired(){
        return true;
    }

}
