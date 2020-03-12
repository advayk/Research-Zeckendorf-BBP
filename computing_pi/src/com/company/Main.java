package com.company;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

// Advay Koranne
// An optimization of the BBP Algorithm using Zeckendorf Telescoping
public class Main {
//    private static int[] intArray = new int[]{ 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946};
    private static int[] intArray = new int[]{ 8, 13, 21};

    public static void main(String[] args) {
        for (int k = 1; k < intArray.length; k++) {
            int a = 0;
            int b = intArray[k];
            int previous_fib = intArray[k-1];
            int iter = 1;
            Double total1 = 0.0;
            for (int i = 0; i < iter; i++) {
                long startTime = System.nanoTime();
                BigDecimal pi = BBP(a,b);
                System.out.println("bbp: " + pi);
                long BBPTime = System.nanoTime() - startTime;
                total1 += BBPTime;

            }
            System.out.println(total1);
        }
        System.out.println("------------");
        for (int k = 1; k < intArray.length; k++) {
            int a = 0;
            int b = intArray[k];
            int previous_fib = intArray[k-1];
            int iter = 1;

            Double total2 = 0.0;

            for (int i = 0; i < iter; i++) {
                String nth = (String.valueOf(BBP(0, previous_fib)));
                long startTime2 = System.nanoTime();
                BigDecimal pi = zeckAlgo(0, b - previous_fib, previous_fib, nth);
                System.out.println("zek: " + pi);

                long zeckoBBPTime = System.nanoTime() - startTime2;
                total2 += zeckoBBPTime;
            }
            System.out.println(total2);
        }

    }

    public static BigDecimal BBP(int a, int b) {
        BigDecimal pi = BigDecimal.ZERO;
        BigDecimal value = BigDecimal.ZERO;
        MathContext precision = new MathContext(10000);
        for (int n = a; n < b; n++) {
            BigDecimal ONE = (((BigDecimal.valueOf(4)).divide((BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n)).add(BigDecimal.ONE)), new MathContext(10000, RoundingMode.HALF_DOWN))));
            BigDecimal TWO = (((BigDecimal.valueOf(2)).divide((BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n)).add(BigDecimal.valueOf(4))), new MathContext(10000, RoundingMode.HALF_DOWN))));
            BigDecimal THREE = (((BigDecimal.valueOf(1)).divide((BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n)).add(BigDecimal.valueOf(5))), new MathContext(10000, RoundingMode.HALF_DOWN))));
            BigDecimal FOUR = (((BigDecimal.valueOf(1)).divide((BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n)).add(BigDecimal.valueOf(6))), new MathContext(10000, RoundingMode.HALF_DOWN))));
            BigDecimal hex = BigDecimal.valueOf((1.0 / 16.0));
            value = hex.pow(n).multiply(ONE.subtract(TWO).subtract(THREE).subtract((FOUR)));
            pi = (pi.add(value));
        }
        return pi;
    }


    public static BigDecimal zeckAlgo(int a, int b, int c, String nth) {
        BigDecimal pi = BigDecimal.ZERO;
        for (int n = a; n < b; n++) {
            BigDecimal ONE = (((BigDecimal.valueOf(4)).divide((BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n+c)).add(BigDecimal.ONE)),new MathContext(10000, RoundingMode.HALF_DOWN))));
            BigDecimal TWO = (((BigDecimal.valueOf(2)).divide((BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n+c)).add(BigDecimal.valueOf(4))), new MathContext(10000, RoundingMode.HALF_DOWN))));
            BigDecimal THREE = (((BigDecimal.valueOf(1)).divide((BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n+c)).add(BigDecimal.valueOf(5))), new MathContext(10000, RoundingMode.HALF_DOWN))));
            BigDecimal FOUR = (((BigDecimal.valueOf(1)).divide((BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(n+c)).add(BigDecimal.valueOf(6))),new MathContext(10000, RoundingMode.HALF_DOWN))));
            BigDecimal hex = BigDecimal.valueOf((1.0 / 16.0));
            BigDecimal value = hex.pow(n+c).multiply(ONE.subtract(TWO).subtract(THREE).subtract((FOUR)));
            pi = (pi.add(value));
        }
        return (pi.add(new BigDecimal(nth)));
    }


    public static String value_of_pi() {
        return "Actual value of pi = 3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381964428810975665933446128475648233";

    }
}
