import java.util.Arrays;

public class RIPEMD320 {

    private static int f(int j, int x, int y, int z) {
        if(j < 16 && j >= 0)
            return x ^ y ^ z;
        if(j < 32)
            return (x & y) | (~x & z);
        if(j < 48)
            return (x | ~y) ^ z;
        if(j < 64)
            return (x & z) | (y & ~z);
        return x ^ (y | ~z);
    }

    private static int K1(int j) {
        if(j < 16 && j >= 0)
            return 0x00000000;
        if(j < 32)
            return 0x5A827999;
        if(j < 48)
            return 0x6ED9EBA1;
        if(j < 64)
            return 0x8F1BBCDC;
        return 0xA953FD4E;
    }

    private static int K2(int j) {
        if(j < 16 && j >= 0)
            return 0x50A28BE6;
        if(j < 32)
            return 0x5C4DD124;
        if(j < 48)
            return 0x6D703EF3;
        if(j < 64)
            return 0x7A6D76E9;
        return 0x00000000;
    }

    private static final int[] R1 = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            7, 4, 13, 1, 10, 6, 15, 3, 12, 0, 9, 5, 2, 14, 11, 8,
            3, 10, 14, 4, 9, 15, 8, 1, 2, 7, 0, 6, 13, 11, 5, 12,
            1, 9, 11, 10, 0, 8, 12, 4, 13, 3, 7, 15, 14, 5, 6, 2,
            4, 0, 5, 9, 7, 12, 2, 10, 14, 1, 3, 8, 11, 6, 15, 13
    };

    private static final int[] R2 = {
            5, 14, 7, 0, 9, 2, 11, 4, 13, 6, 15, 8, 1, 10, 3, 12,
            6, 11, 3, 7, 0, 13, 5, 10, 14, 15, 8, 12, 4, 9, 1, 2,
            15, 5, 1, 3, 7, 14, 6, 9, 11, 8, 12, 2, 10, 0, 4, 13,
            8, 6, 4, 1, 3, 11, 15, 0, 5, 12, 2, 13, 9, 7, 10, 14,
            12, 15, 10, 4, 1, 5, 8, 7, 6, 2, 13, 14, 0, 3, 9, 11
    };

    private static final int[] S1 = {
            11, 14, 15, 12, 5, 8, 7, 9, 11, 13, 14, 15, 6, 7, 9, 8,
            7, 6, 8, 13, 11, 9, 7, 15, 7, 12, 15, 9, 11, 7, 13, 12,
            11, 13, 6, 7, 14, 9, 13, 15, 14, 8, 13, 6, 5, 12, 7, 5,
            11, 12, 14, 15, 14, 15, 9, 8, 9, 14, 5, 6, 8, 6, 5, 12,
            9, 15, 5, 11, 6, 8, 13, 12, 5, 12, 13, 14, 11, 8, 5, 6
    };

    private static final int[] S2 = {
            8, 9, 9, 11, 13, 15, 15, 5, 7, 7, 8, 11, 14, 14, 12, 6,
            9, 13, 15, 7, 12, 8, 9, 11, 7, 7, 12, 7, 6, 15, 13, 11,
            9, 7, 15, 11, 8, 6, 6, 14, 12, 13, 5, 14, 13, 13, 7, 5,
            15, 5, 8, 11, 14, 14, 6, 14, 6, 9, 12, 9, 12, 5, 15, 8,
            8, 5, 12, 9, 12, 5, 14, 6, 8, 13, 6, 5, 15, 13, 11, 11
    };

    private static final int[] H = {
            0x67452301,
            0xEFCDAB89,
            0x98BADCFE,
            0x10325476,
            0xC3D2E1F0,
            0x76543210,
            0xFEDCBA98,
            0x89ABCDEF,
            0x01234567,
            0x3C2D1E0F
    };

    private static byte[] createArray(String text) {
        byte[] bytes = text.getBytes();
        int length = bytes.length;
        int remainder = (length << 3) % 512;
        int numToAdd;
        if (remainder < 448) {
            numToAdd = 448 - remainder;
        } else {
            numToAdd = 512 - remainder + 448;
        }
        numToAdd >>= 3;

        int resultLength = length + numToAdd + 8;
        byte[] result = new byte[resultLength];
        System.arraycopy(bytes, 0, result, 0, length);

        result[length] = (byte) 0x80;
        for (int i = length + 1; i < resultLength - 8; i++) {
            result[i] = 0x00;
        }
        length <<= 3;
        for (int i = resultLength - 8; i < resultLength; i++) {
            result[i] = (byte) (length % 0xFF);
            length >>>= 8;
        }
        return result;
    }
    private static String createString(int h[]) {

        byte[] resultBytes = new byte[40];
        for (int i = 0; i < resultBytes.length / 4; i++) {
            resultBytes[i * 4] = (byte) h[i];
            resultBytes[i * 4 + 1] = (byte) (h[i] >>> 8);
            resultBytes[i * 4 + 2] = (byte) (h[i] >>> 16);
            resultBytes[i * 4 + 3] = (byte) (h[i] >>> 24);
        }
        StringBuilder answer = new StringBuilder();
        for (byte resultByte : resultBytes) {
            if (resultByte > 0 && resultByte <= 15)
                answer.append("0").append(Integer.toHexString(resultByte & 0xFF));
            else if (resultByte == 0)
                answer.append("00");
            else
                answer.append(Integer.toHexString(resultByte & 0xFF));
        }
        return answer.toString();
    }
    private static int leftShift(int num, int round) {
        return (num << round) | (num >>> (32 - round));
    }

    public static String hash(String text) {
        byte[] bytes = createArray(text);
        int t = bytes.length/64;
        int A, B, C, D, E, A2, B2, C2, D2, E2, T;
        int h[] = H.clone();
        for(int i = 0; i < t; i++) {
            A = h[0];
            B = h[1];
            C = h[2];
            D = h[3];
            E = h[4];
            A2 = h[5];
            B2 = h[6];
            C2 = h[7];
            D2 = h[8];
            E2 = h[9];

            int[] X = new int[16];
            for (int j = 0; j < 16; j++) {
                X[j] = (bytes[i*64 + j*4] & 0xFF) |
                        (bytes[i*64 + j*4+1] & 0xFF) << 8 |
                        (bytes[i*64 + j*4 + 2] & 0xFF) << 16 |
                        bytes[i*64 + j*4 + 3] << 24;
            }
            for(int j = 0; j < 80; j++) {

                T = A + f(j, B, C, D) + X[R1[j]] + K1(j);
                T = leftShift(T, S1[j]) + E;
                A = E;
                E = D;
                D = leftShift(C, 10);
                C = B;
                B = T;

                T = A2 + f(79 - j, B2, C2, D2) + X[R2[j]] + K2(j);
                T = leftShift(T, S2[j]) + E2;
                A2 = E2;
                E2 = D2;
                D2 = leftShift(C2, 10);
                C2 = B2;
                B2 = T;

                if(j == 15) {
                    T = B;
                    B = B2;
                    B2 = T;
                } else if(j == 31) {
                    T = D;
                    D = D2;
                    D2 = T;
                } else if(j == 47) {
                    T = A;
                    A = A2;
                    A2 = T;
                } else if(j == 63) {
                    T = C;
                    C = C2;
                    C2 = T;
                } else if(j == 79) {
                    T = E;
                    E = E2;
                    E2 = T;
                }
            }
            h[0] += A;
            h[1] += B;
            h[2] += C;
            h[3] += D;
            h[4] += E;
            h[5] += A2;
            h[6] += B2;
            h[7] += C2;
            h[8] += D2;
            h[9] += E2;
        }
        return createString(h);
    }
}
