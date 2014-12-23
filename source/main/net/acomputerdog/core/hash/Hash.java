package net.acomputerdog.core.hash;

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
    /**
     * A prime number to use as a multiplicand for SEED
     */
    private static final int PRIME = 37;

    /**
     * Number to use to seed hash values.
     */
    public static final int SEED = 19;

    /**
     * Hash a boolean value
     *
     * @param seed The seed (value of previous hashes or SEED).
     * @param val  The value to hash
     * @return Return the hashed value
     */
    public static int hash(int seed, boolean val) {
        return firstTerm(seed) + (val ? 1 : 0);
    }

    /**
     * Hash a char value
     * @param seed The seed (value of previous hashes or SEED).
     * @param val The value to hash
     * @return Return the hashed value
     */
    public static int hash(int seed, char val) {
        return firstTerm(seed) + (int) val;
    }

    /**
     * Hash a long value
     * @param seed The seed (value of previous hashes or SEED).
     * @param val The value to hash
     * @return Return the hashed value
     */
    public static int hash(int seed, long val) {
        return firstTerm(seed) + (int) (val ^ (val >>> 32));
    }

    /**
     * Hash a float value
     * @param seed The seed (value of previous hashes or SEED).
     * @param val The value to hash
     * @return Return the hashed value
     */
    public static int hash(int seed, float val) {
        return hash(seed, Float.floatToIntBits(val));
    }

    /**
     * Hash a double value
     * @param seed The seed (value of previous hashes or SEED).
     * @param val The value to hash
     * @return Return the hashed value
     */
    public static int hash(int seed, double val) {
        return hash(seed, Double.doubleToLongBits(val));
    }

    /**
     * Hash an int value
     * @param seed The seed (value of previous hashes or SEED).
     * @param val The value to hash
     * @return Return the hashed value
     */
    public static int hash(int seed, int val) {
        return firstTerm(seed) + val;
    }

    /**
     * Hash an object
     * @param seed The seed (value of previous hashes or SEED).
     * @param obj the object to be hashed
     * @return Return the hashed value
     */
    public static int hash(int seed, Object obj) {
        int result = seed;
        if (obj == null) {
            result = hash(result, 0);
        } else if (!isArray(obj)) {
            result = hash(result, obj.hashCode());
        } else {
            int length = Array.getLength(obj);
            for (int index = 0; index < length; ++index) {
                Object item = Array.get(obj, index);
                if (!(item == obj)) {
                    result = hash(result, item);
                }
            }
        }
        return result;
    }

    /**
     * Finds the initial term in a hash sequence
     * @param seed The seed to calculate
     * @return Return the initial term in a hash sequence
     */
    private static int firstTerm(int seed) {
        return PRIME * seed;
    }

    /**
     * Checks if an object is an array
     * @param obj The object
     * @return Return true if the object is an array
     */
    private static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }
}
