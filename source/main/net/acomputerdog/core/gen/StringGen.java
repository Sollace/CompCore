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
    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ0123456789".toCharArray();

    /**
     * Creates a string of the specified length
     *
     * @param length The length of the string
     * @return Return the generated string
     */
    public static String nextString(int length) {
		byte[] bytes = new byte[length];
		char[] buf = new char[length];
		random.nextBytes(bytes);
		byte b;
		while (length-- > 0) {
			buf[length] = alphabet[((b = bytes[length]) < 0 ? -b : b) % alphabet.length];
		}
		return new String(buf);
    }

    /**
     * Gets a random 8-character String
     * @return The generated String.
     */
    public static String nextString() {
        return nextString(8);
    }
}
