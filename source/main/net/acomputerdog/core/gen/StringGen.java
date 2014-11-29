package net.acomputerdog.core.gen;

import java.util.Random;

public class StringGen {
    private static final Random random = new Random("StringGen".hashCode() + System.currentTimeMillis());
    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ".toCharArray();

    public static int nextInt() {
        return random.nextInt();
    }

    public static String nextString(int length) {
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        limBytes(bytes, (byte) alphabet.length);
        return createString(bytes);
    }

    public static String nextString() {
        return nextString(8);
    }

    private static String createString(byte[] bytes) {
        char[] chars = new char[bytes.length];
        for (int index = 0; index < bytes.length; index++) {
            bytes[index] = (byte) (bytes[index] % chars.length);
        }
        return new String(chars);
    }

    private static byte[] limBytes(byte[] bytes, byte max) {
        for (int index = 0; index < bytes.length; index++) {
            bytes[index] %= max;
        }
        return bytes;
    }
}
