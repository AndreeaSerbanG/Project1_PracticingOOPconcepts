public class Algorithms {
    //metoda pt a verifica daca numarul este perfect
    public static boolean isPerfect( int n){
        int sum = 1;
        for( int i = 2; i <= n/2; i++){
            if( n % i == 0){
                sum += i;
            }
        }
        if (n != sum) {
          return false;
        }
        return true;
    }
    //metoda pt a verifica daca numarul este prim
    public static boolean isPrime ( int n){
        for ( int i = 2; i <= Math.sqrt(n); i++){
            if ( n % i == 0){
                return false;
            }
        }
        return true;
    }

    //metoda pt a verifica daca numarul reprezinta o capcana
    public static boolean isTrap( int n){
        int sum = 0;
        int _n = n;
        while( _n!=0){
            int lastDigit = _n % 10;
            sum += lastDigit;
            _n /= 10;
        }
        if ( n % 2 == 0 && sum % 3 == 0){
            return true;
        }
        return false;
    }

    //metoda de calcul a probabilitatii
    public static double probability( int health, long score){
         double probability = (3 * health*1.0d + score* 1.0d/1000)/4;
         return probability;
    }
}
