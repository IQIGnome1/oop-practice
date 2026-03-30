public class Main {

    public static void main(String[] args) {

        //tegelased
        Dude fighter = new Fighter();
        Dude wizard = new Wizard();

        // duell kuni üks sureb ära
        while (fighter.isAlive() && wizard.isAlive()) {

            fighter.takeTurn(wizard);

            if (!wizard.isAlive()) {
                break;
            }

            wizard.takeTurn(fighter);
        }

        // kuvame võitja
        if (fighter.isAlive()) {
            System.out.println("Fighter wins!");
        } else {
            System.out.println("Wizard wins!");
        }
    }
}