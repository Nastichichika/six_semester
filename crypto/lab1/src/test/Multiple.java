package test;

import algorithms.Karatsuba;
import algorithms.Montgomery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

public class Multiple {
    private static final Random random = new Random();
    public static BigInteger getRandomOdd(int bitLength) {
        while(true) {
            BigInteger a = new BigInteger(bitLength, Multiple.random);
            if(a.testBit(0))
                return a;
        }
    }
    @Test
    public void testKaratsuba() {
        BigInteger a = new BigInteger(512,random);
        BigInteger b = new BigInteger(512,random);
        Assertions.assertEquals(a.multiply(b), Karatsuba.multiply(a, b));
    }
    @Test
    public void montgomeryMultiplyTest() {
        BigInteger a = new BigInteger(512,random);
        BigInteger b = new BigInteger(512,random);
        BigInteger base = getRandomOdd(512);
        Assertions.assertEquals(a.multiply(b).mod(base), Montgomery.multiply(a, b, base));

    }
}
