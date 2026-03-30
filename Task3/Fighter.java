public class Fighter extends Dude {

    public Fighter() {
        this.accuracy = 6;
        this.armor = 14;
        this.health = 120;
        this.actionPoints = 10;
    }

    @Override
    Effect chooseEffect() {

        // juhuslik efekt
        int choice = (int)(Math.random() * 2);

        if (choice == 0) {
            return new WeaponAttack(15);
        } else {
            return new Knockdown();
        }
    }

    @Override
    int getActionPointsRegen() {
        // action pointide taastumine
        return 5;
    }
}