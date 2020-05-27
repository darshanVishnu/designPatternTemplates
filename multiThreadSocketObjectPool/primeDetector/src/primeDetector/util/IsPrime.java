package primeDetector.util;

/**
 * class IsPrime used for check a number is prime or not
 */
public class IsPrime {
    /**
     * IsNumPrime used to check if a num is prime or not
     *
     * @param num must of type int
     * @return result which is a prime number
     */
    public synchronized boolean isNumPrime(int num) {
        boolean result = true;
        if (num == 2) {
            return true;
        }
        int sqrt = (int) Math.ceil(Math.sqrt((double) num));
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}