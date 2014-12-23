package net.acomputerdog.core.gen;

import java.util.Random;

/**
 * Utility class for generating (insecure) random strings
 */
public class StringGen {
    /**
     * Instance of Random for generating strings
     */
    private static final Random random = new Random("StringGen".hashCode() + System.currentTimeMillis());

    /**
     * "Alphabet" of characters to use when generating Strings
     */
    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ01234567890".toCharArray();

    /**
     * Creates a string of the specified length
     *
     * @param length The length of the string
     * @return Return the generated string
     */
    public static String nextString(int length) {
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        limBytes(bytes, (byte) alphabet.length);
        char[] chars = new char[bytes.length];
        for (int index = 0; index < bytes.length; index++) {
            bytes[index] = (byte) (bytes[index] % chars.length);
        }
        return new String(chars);
    }

    /**
     * Gets a random 8-character String
     * @return The generated String.
     */
    public static String nextString() {
        return nextString(8);
    }

    /**
     * Limits an array of bytes to a certain max value using modulus
     * @param bytes The bytes to limit
     * @param max The value to limit to
     * @return Return the array of bytes with limited values
     */
    private static byte[] limBytes(byte[] bytes, byte max) {
        for (int index = 0; index < bytes.length; index++) {
            bytes[index] %= max;
        }
        return bytes;
    }
}
