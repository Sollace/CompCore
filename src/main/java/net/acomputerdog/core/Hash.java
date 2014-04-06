package net.acomputerdog.core;

import java.lang.reflect.Array;

/**
 * Simple HashCode generator, based on code by Joshua Bloch.  Use like:
 * public int hashCode() {
 * int result = HashCode.SEED;
 * result = HashCode.hash(result, field_a);
 * result = HashCode.hash(result, field_b);
 * result = HashCode.hash(result, field_c);
 * result = HashCode.hash(result, field_d);
 * result = HashCode.hash(result, field_e);
 * result = HashCode.hash(result, field_f);
 * return result;
 * }
 */
public class Hash {
    private static final int PRIME = 37;
    public static final int SEED = 19;

    public static int hash(int seed, boolean val) {
        return firstTerm(seed) + (val ? 1 : 0);
    }

    public static int hash(int seed, char val) {
        return firstTerm(seed) + (int) val;
    }

    public static int hash(int seed, long val) {
        return firstTerm(seed) + (int) (val ^ (val >>> 32));
    }

    public static int hash(int seed, float val) {
        return hash(seed, Float.floatToIntBits(val));
    }

    public static int hash(int seed, double val) {
        return hash(seed, Double.doubleToLongBits(val));
    }

    public static int hash(int seed, int val) {
        return firstTerm(seed) + val;
    }

    public static int hash(int seed, Object obj) {
        int result = seed;
        if (obj == null) {
            result = hash(result, 0);
        } else if (!isArray(obj)) {
            result = hash(result, obj.hashCode());
        } else {
            int length = Array.getLength(obj);
            for (int idx = 0; idx < length; ++idx) {
                Object item = Array.get(obj, idx);
                if (!(item == obj)) {
                    result = hash(result, item);
                }
            }
        }
        return result;
    }

    private static int firstTerm(int seed) {
        return PRIME * seed;
    }

    private static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }
}
