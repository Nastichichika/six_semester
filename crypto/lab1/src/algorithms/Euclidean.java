package algorithms;

import java.math.BigInteger;

public class Euclidean {

    public Euclidean(){}

    public static BigInteger[] gcdExtended(BigInteger a, BigInteger b) {
        BigInteger[] arrDivRem;
        BigInteger[] result = new BigInteger[3];
        BigInteger x = a;
        BigInteger y = b;

        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;

        while (true){

            arrDivRem = x.divideAndRemainder(y);
            x = arrDivRem[1];
            if (x.equals(BigInteger.ZERO)) {
                result[0]=y;
                result[1]=y0;
                result[2]=y1;
                return result;
            }

            x0 = x0.subtract(y0.multiply(arrDivRem[0]));
            x1 = x1.subtract(y1.multiply(arrDivRem[0]));

            arrDivRem = y.divideAndRemainder(x);
            y = arrDivRem[1];
            if (y.equals(BigInteger.ZERO)) {
                result[0]=x;
                result[1]=x0;
                result[2]=x1;
                return result;
            }

            y0 = y0.subtract(x0.multiply(arrDivRem[0]));
            y1 = y1.subtract(x1.multiply(arrDivRem[0]));

        }
    }

}
