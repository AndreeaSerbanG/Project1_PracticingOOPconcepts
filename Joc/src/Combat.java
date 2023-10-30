import java.util.Random;

public class Combat {

    //metoda de afisare a actualizarilor
    public  static void printUpdatedFields( PlayerStatus player){
        System.out.println("\u2733 Actualizari pentru " + player.getNickname() +
                ": \n\t\u2714 Pozitie -> " + player.getPositionX() + ", " + player.getPositionY() +
                "\n\t\u2714 Scor -> " + player.getScore() + "\n\t\u2714 Sanatate -> " + player.getHealth() +
                "\n\t\u2714 Vieti -> " + player.getLives());
    }

    //metoda pentru alocarea unei arme aleatorii
    public static void randomWeapon(PlayerStatus player) {
        Random random = new Random();

        if(player.getScore() < 1_000){
            player.movePlayerTo(player.getPositionX() + 1, player.getPositionY() + 1);
            player.findArtifact(3);
        }

        do {
            int randomWeapon = random.nextInt(1, 4);
            System.out.print("\n\uD83D\uDDE1Actualizare arma pentru " + player.getNickname() + ": ");
            switch (randomWeapon) {
                case 1:
                    System.out.println(player.setWeaponInHand("knife"));
                    break;
                case 2:
                    System.out.println(player.setWeaponInHand("sniper"));
                    break;
                case 3:
                    System.out.println(player.setWeaponInHand("kalashnikov"));
                    break;
            }
        } while (player.getWeaponInHand() == null);

    }

    //metoda de atac intiata de player contra opponent
    public static void attack(PlayerStatus player, PlayerStatus opponent) {

        int i = 1;
        double distanta = Math.sqrt(Math.pow((player.getPositionX() - opponent.getPositionX()), 2)
                + Math.pow((player.getPositionY() - opponent.getPositionY()), 2));

        System.out.println("\uD83D\uDCCF Distanta intre jucatori este de " + distanta + " metri.");
        System.out.println("\u2694 " + player.getNickname() + " poate castiga un duel impotriva lui "
                + opponent.getNickname() + "\n\n~~~~~ Se intializeaza atacul ~~~~~\n");

        do {
            System.out.println("\tDupa atacul " + i + ": ");

            //actualizarea campului health in functie de arma cu care se face atacul
            // si distanta dintre jucatori
            switch (player.getWeaponInHand()) {
                case "knife":
                    opponent.setHealth(opponent.getHealth() - 25);
                    break;
                case "sniper":
                    if (distanta > 100) {
                        opponent.setHealth(opponent.getHealth() - 100);
                    } else {
                        opponent.setHealth((opponent.getHealth()) - 50);
                    }
                    break;
                case "kalashnikov":
                    if (distanta > 100) {
                        opponent.setHealth((opponent.getHealth()) - 75);
                    } else {
                        opponent.setHealth(opponent.getHealth() - 100);
                    }
            }
            if (opponent.getHealth() <= 0) {
                opponent.setLives(opponent.getLives() - 1);

                System.out.println("\u274C Jucatorul " + opponent.getNickname() + " a pierdut o viata.");
            }else{
                System.out.println("\u2705 Sanatate " + opponent.getNickname() + ": " + opponent.getHealth());
            }
            i++;
        } while (opponent.getHealth() > 0);

        //resetarea campurilor pozitie si sanatate
        opponent.setHealth(100);
        opponent.setPositionX(0);
        opponent.setPositionY(0);

        //afisarea starii dupa duel
        System.out.println("\n\u2734 Starea jucatorului " + opponent.getNickname() + " dupa duel este: " +
                ": \n\t\u2B55 Pozitie -> " + opponent.getPositionX() + ", " + opponent.getPositionY() +
                "\n\t\u2B55 Scor -> " + opponent.getScore() + " \n\t\u2B55 Sanatate -> " + opponent.getHealth() +
                "\n\t\u2B55 Vieti -> " + opponent.getLives());

        player.setScore((player.getScore())+5000);
        System.out.println("\uD83D\uDCB0 Scor bonus pentru " + player.getNickname() + " ==> Scor curent: " + player.getScore());
    }

    //metoda de desfasurare a jocului
    public static void run(PlayerStatus player1, PlayerStatus player2) {
        Random random = new Random();

        //jucatorii se deplaseaza si cauta artefacte
        System.out.println("\uD83D\uDEB6 Jucatorii se deplaseaza si gasesc artefacte.\n");

        do{
            player1.movePlayerTo(player1.getPositionX() + 1, player1.getPositionY() + 2);
            int rand1 = random.nextInt(1, 12);
            player1.findArtifact(rand1);

            player2.movePlayerTo(player2.getPositionX() + 3, player2.getPositionY() + 4);
            int rand2 = random.nextInt(1, 12);
            player2.findArtifact(rand2);

        }while (player1.getScore() <= 10_000 && player2.getScore() <= 10_000);


        //afisarea starii dupa ce cel putin unul dintre jucatori a atins 10_000 de puncte
        printUpdatedFields(player1);
        printUpdatedFields(player2);

        //alegerea unei arme random pt player 1
        randomWeapon(player1);
        System.out.println(" ==> Arma curenta: " + player1.getWeaponInHand());

        //alegerea unei arme random pt player 2
        randomWeapon(player2);
        System.out.println(" ==> Arma curenta: " + player2.getWeaponInHand());

        System.out.println();

        //initierea atacului
        if (player1.shouldAttackOpponent(player2)) {
            attack(player1, player2);
        } else {
            attack(player2, player1);
        }
    }
}
