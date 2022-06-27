package task_3;

import java.math.BigInteger;

public class DigitsSum {
    public static final int NUMBER = 100;

    public static void main(String[] args) {
        BigInteger f = factorial(NUMBER);
        int digitsSumOfNumber = getDigitsSumOfNumber(f);
        System.out.printf("Factorial of %d (%d!): %d\n", NUMBER, NUMBER, f);
        System.out.printf("Sum of digits (%d!): %d\n", NUMBER, digitsSumOfNumber);
    }

    //  Return (n!)
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) { // <-- n, not 100.also, x*1=x
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    //  Recursive method, which return sum of number digits
    public static int getDigitsSumOfNumber(BigInteger number) {
        BigInteger[] dar = number.divideAndRemainder(BigInteger.valueOf(10));
        BigInteger q = dar[0];
        int r = dar[1].intValue();
        if (!q.equals(BigInteger.ZERO)) {
            r += getDigitsSumOfNumber(q);
        }

        return r;
    }
}