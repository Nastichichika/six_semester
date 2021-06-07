package test;
import algorithms.ModPow;
import algorithms.Montgomery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

public class ModPowTest {
    Random random = new Random();
    @Test
    public void testModPow() {
        BigInteger a = new BigInteger(512,random);
        BigInteger e = new BigInteger(512,random);
        BigInteger n = new BigInteger(512,random);
        Assertions.assertEquals(a.modPow(e,n), ModPow.modPow(a,e,n));
    }
    @Test
    public void montgomeryModPowTest() {
        BigInteger a = new BigInteger(512,random);
        BigInteger e = new BigInteger(512,random);
        BigInteger n = new BigInteger(512,random);
        Assertions.assertEquals(a.modPow(e,n), Montgomery.pow(a, e, n));
    }
}
