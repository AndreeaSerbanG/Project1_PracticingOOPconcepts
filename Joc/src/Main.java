public class Main {
    public static void main (String [] Args) {

        //afisare nume joc
        PlayerStatus.setGameName("Battlegrounds");
        System.out.println("\n========== " + PlayerStatus.getGameName() +
                " ========== " + "\n\t\t\u2694 Start Game! \u2694\n");

        //instantierea clasei PLayerStatus
        PlayerStatus player1 = new PlayerStatus();
        PlayerStatus player2 = new PlayerStatus();

        //initializarea campurilor corespunzatoare numelui si pozitiei primului jucator
        player1.initPlayer( "Michael", 3);
        player1.setPositionX(5);
        player1.setPositionY(5);

        //initializarea campurilor corespunzatoare numelui si pozitiei celui de-al doilea jucator
        player2.initPlayer( "Charlie", 3);
        player2.setPositionX(10);
        player2.setPositionY(10);

        //afisare starii jucatorilor
        player1.printPlayerStatus();
        player2.printPlayerStatus();

        System.out.println();

        //desfasurarea jocului pana cand unul dintre jucatori ramane fara vieti
        int i = 1;
        while( player1.getLives()!= 0 && player2.getLives()!= 0) {
            Combat.run(player1, player2);
            System.out.println("\n~~~~~S-a incheiat duelul " + i + " ~~~~~\n");
            i++;
        }
        if( player1.getLives() != 0){
            System.out.println("\uD83C\uDFC6 Jocul a fost castigat de " + player1.getNickname());
        }else{
            System.out.println("\uD83C\uDFC6 Jocul a fost castigat de " + player2.getNickname());
        }
        System.out.println("\n======== \u2620 Game over! \u2620 ========");

    }
}