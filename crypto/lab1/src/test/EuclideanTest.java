package test;
import algorithms.Euclidean;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EuclideanTest {
    Random random = new Random();
    @Test
    public void gcdExtendedTest(){
        BigInteger a = new BigInteger(512, random);
        BigInteger b = new BigInteger(512, random);
        BigInteger[] res = Euclidean.gcdExtended(a, b);
        assertEquals(a.gcd(b), res[0]);
        assertEquals(a.multiply(res[1]).add(b.multiply(res[2])), res[0]);
    }
}
