package test;
import algorithms.Fermat;
import algorithms.MillerRabin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class TestPrimes {
    @Test
    public void testFermat() {
        Assertions.assertTrue(Fermat.checkPrime(new BigInteger(String.valueOf(3571)), 50));
        Assertions.assertFalse(Fermat.checkPrime(new BigInteger(String.valueOf(89563130)), 50));
    }
    @Test
    public void testMillerRabin() {
        Assertions.assertTrue(MillerRabin.checkPrime(new BigInteger(String.valueOf(3571)), 50));
        Assertions.assertFalse(MillerRabin.checkPrime(new BigInteger(String.valueOf(89563130)), 50));
    }
}
