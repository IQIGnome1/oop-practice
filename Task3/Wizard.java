public class Wizard extends Dude {

    public Wizard() {
        this.accuracy = 8;
        this.armor = 10;
        this.health = 80;
        this.actionPoints = 12;
    }

    @Override
    Effect chooseEffect() {
        int choice = (int) (Math.random() * 2);

        if (choice == 0) {
            return new Firebolt(20);
        } else {
            return new Spiderweb(3);
        }
    }

    @Override
        // mitu action pointi taastub käigu lõpus
    int getActionPointsRegen() {
        return 6;
    }
}