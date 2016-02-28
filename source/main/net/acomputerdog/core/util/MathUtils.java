package net.acomputerdog.core.util;

/**
 * Math utility functions
 */
public class MathUtils {
    /**
     * Checks if a string represents a number.
     *
     * @param str The string to test.
     * @return Return true if the string is an integer.
     */
    public static boolean isInteger(String str) {
        if (str == null) return false;
        final char[] chars = str.toCharArray();
        if (chars.length == 0) return false;
        int i = 0;
        if (chars[0] == '-' || chars[0] == '+') {
            if (chars.length == 1) return false;
            i++;
        }
        for (; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) return false;
        }
        return true;
    }
    
    /**
     * Checks if a string represents a number with support for floating points.
     *
     * @param str The string to test.
     * @return Return true if the string is a number.
     */
    public static boolean isFloat(String str) {
    	if (str == null) return false;
        final char[] chars = str.toCharArray();
        if (chars.length == 0) return false;
        int i = 0;
        if (chars[0] == '-' || chars[0] == '+') {
            if (chars.length == 1) return false;
            i++;
        }
        boolean dotted = false;
        for (; i < chars.length; i++) {
        	if (chars[i] == '.' || chars[i] == ',') {
        		if (dotted) return false;
        		dotted = true;
        	}
            if (!Character.isDigit(chars[i])) return false;
        }
        return true;
    }
}
