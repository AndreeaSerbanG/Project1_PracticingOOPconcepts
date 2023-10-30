public class PlayerStatus {

    //starea internă a obiectelor de tip player
    private String nickname;
    private long score;
    private int lives;
    private int health;  // între 0 și 100, ce reprezintă procentul de viață rămas.
    private String weaponInHand;
    private double positionX;
    private double positionY;
    private static String gameName;

    //comportamentul obiectelor

    //metode de initializare
    public void initPlayer(String nickname, int lives, long score){
        this.nickname = nickname;
        this.lives = lives;
        this.score = score;
    }

    public void initPlayer(String nickname, int lives) {
        initPlayer(nickname, lives, 0);
    }

    public void initPlayer(String nickname) {
        initPlayer(nickname, 0);
    }

    //metode pt a actualiza campul health si pt a-i returna valoarea
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setScore(long score) {
        this.score = score;
    }
    //metoda pt a afisa valoarea campului scor
    public long getScore() {
        return score;
    }

    //metode pt a actualiza valoarea campului lives si pt a-i returna valoarea
    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    //metoda de modificare a campurilor score, lives si health
    public void findArtifact(int artifactCode) {
        if (Algorithms.isPerfect(artifactCode)) {
            score += 5000;
            lives += 1;
            health = 100;
        } else if (Algorithms.isPrime(artifactCode)) {
            score += 1000;
            lives += 2;
            health += 25;
            if (health >= 100) {
                health = 100;
            }
        } else if (Algorithms.isTrap(artifactCode)) {
            score -= 3000;
            health -= 25;
            if (health == 0) {
                lives -= 1;
                health = 100;
            } else if (health < 0) {
                lives -= 1;
                health = 100 + health;
            }
        } else {
            score += artifactCode;
        }
        if (lives > 3) {
            lives = 3;
        }
    }

    //metoda pt a afisa daca arma jucatorului s-a actualizat sau nu
    public boolean setWeaponInHand(String weaponInHand) {
        if (this.weaponInHand != null && this.weaponInHand.equals(weaponInHand)) {
            return false;
        }
        switch (weaponInHand) {
            case "knife":
                if (score >= 1_000) {
                    score -= 1_000;
                    this.weaponInHand = weaponInHand;
                    return true;
                }
            case "sniper":
                if (score >= 10_000) {
                    score -= 10_000;
                    this.weaponInHand = weaponInHand;
                    return true;
                }
            case "kalashnikov":
                if (score >= 20_000) {
                    score -= 20_000;
                    this.weaponInHand = weaponInHand;
                    return true;
                }
            default:
                return false;
        }

    }

    //metoda care returneaza arma curenta a jucatorului
    public String getWeaponInHand() {
        return weaponInHand;
    }

    //metode pt setarea campurilor positionX si positionY, respectiv pt returnarea valorilor acestora
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionY() {
        return positionY;
    }

    //metode care seteaza numele jocului
    protected static void setGameName(String gameName) {
        PlayerStatus.gameName = gameName;
    }

    //metode care returneaza numele jocului
    public static String getGameName() {
        return gameName;
    }

    //metoda prin care se modifica pozitia jucatorului
    public void movePlayerTo(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    //metoda care returneaza numele jucatorului
    public String getNickname() {
        return nickname;
    }

    //metoda pt a afla sansele de castig ale jucatorului, in cazul initierii unui atac impotriva oponentului sau
    public boolean shouldAttackOpponent(PlayerStatus opponent) {
        if (this.weaponInHand.equals(opponent.weaponInHand)) {
            if (Algorithms.probability(this.health, this.score) > Algorithms.probability(opponent.health, opponent.score)) {
                return true;
            }
        } else {
            double distanta = Math.sqrt(Math.pow((this.positionX - opponent.positionX), 2)
                    + Math.pow((this.positionY - opponent.positionY), 2));
            if (distanta > 1000) {
                switch (this.weaponInHand) {
                    case "sniper":
                        return true;
                    case "kalashnikov":
                        if (opponent.weaponInHand.equals("knife")) {
                            return true;
                        } else {
                            return false;
                        }
                    default:
                        return false;
                }
            }
            if (distanta <= 1000) {
                switch (this.weaponInHand) {
                    case "kalashnikov":
                        return true;
                    case "sniper":
                        if (opponent.weaponInHand.equals("knife")) {
                            return true;
                        } else {
                            return false;
                        }
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    //metoda de afisare a starii jucatorului
    public void printPlayerStatus() {
        System.out.println("\u2734 Starea jucatorului " + nickname +
                " este :\n\t\u2B55 Scor -> " + score + "\n\t\u2B55 Sanatate -> " + health +
                "\n\t\u2B55 Vieti -> " + lives + "\n\t\u2B55 Arma utilizata -> " + weaponInHand +
                "\n\t\u2B55 Pozitie in joc (x,y) -> " + getPositionX() + ", " + getPositionY());
    }

}
