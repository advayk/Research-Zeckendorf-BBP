package com.company;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

// Advay Koranne
// An optimization of calculate e using Zeckendorf Telescoping
public class e {
    private static int[] intArray = new int[]{ 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025};
    public static void main(String[] args) {
        for (int k = 1; k < intArray.length; k++) {
            BigInteger a = BigInteger.ONE;
            int b = 1000;
            int previous_fib = intArray[k-1];
            int iter = 1;
            Double total1 = 0.0;
            for (int i = 0; i < iter; i++) {
                long startTime = System.nanoTime();
                BigDecimal e = eBBP(b);
                System.out.println(e);
                long BBPTime = System.nanoTime() - startTime;
                total1 += BBPTime;
            }
            System.out.println(total1);
        }
        System.out.println("------------");
        for (int k = 1; k < intArray.length; k++) {
            BigInteger a = BigInteger.ONE;
            int b = intArray[k];
            int previous_fib = intArray[k-1];
            int iter = 1;

            Double total2 = 0.0;

            for (int i = 0; i < iter; i++) {
                String nth = (String.valueOf(eBBP(previous_fib)));
                long startTime2 = System.nanoTime();
                BigDecimal e = zeckAlgoE(b - previous_fib, previous_fib, nth);
                long zeckoBBPTime = System.nanoTime() - startTime2;
                total2 += zeckoBBPTime;
            }
            System.out.println(total2);
        }

    }

    public static BigDecimal eBBP(int b) {
        BigDecimal sum = BigDecimal.ONE;
        MathContext precision = new MathContext(10000);
        for (BigInteger n = BigInteger.valueOf(1); n.compareTo(BigInteger.valueOf(b)) <= 0; n = n.add(BigInteger.ONE)) {
            sum = (sum.add(BigDecimal.ONE.divide(new BigDecimal((factorial(n))), new MathContext(10000, RoundingMode.HALF_DOWN))));
        }
        return sum;
    }


    public static BigDecimal zeckAlgoE(int b, long c, String nth) {
        BigDecimal sum = BigDecimal.ONE;
        for (BigInteger n = BigInteger.valueOf(1); n.compareTo(BigInteger.valueOf(b)) <= 0; n = n.add(BigInteger.ONE)) {
            sum = (sum.add(BigDecimal.ONE.divide(new BigDecimal((n.add(BigInteger.valueOf(c))))), new MathContext(10000, RoundingMode.HALF_DOWN)));
        }
        return (sum.add(new BigDecimal(nth)));
    }
    

    public static BigInteger factorial(BigInteger n) {

        BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
        if(n.compareTo(BigInteger.valueOf(1)) == 0) {
            return BigInteger.ONE;
        }
        if (n.compareTo(two) <= 0) {
            return n;
        }
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }


}
