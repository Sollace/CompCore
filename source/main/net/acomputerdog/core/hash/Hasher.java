package net.acomputerdog.core.hash;

/**
 * Utility class to assist with calculating HashCodes.
 * Use like:
 * public int hashCode() {
 * return new Hasher().hash(val1).hash(val2).hash(val3).hashCode();
 * }
 */
public class Hasher {
    /**
     * The current accumulated value of this Hasher
     */
    private int currentHashValue;

    /**
     * Creates a new Hasher
     */
    public Hasher() {
        currentHashValue = Hash.SEED;
    }

    /**
     * Hash an Object
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(Object val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    /**
     * Hash an int
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(int val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    /**
     * Hash a byte
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(byte val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    /**
     * Hash a short
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(short val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    /**
     * Hash a long
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(long val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    /**
     * Hash a boolean
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(boolean val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    /**
     * Hash a float
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(float val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    /**
     * Hash a double
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(double val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    /**
     * Hash a char
     * @param val the value to hash
     * @return Return this hasher
     */
    public Hasher hash(char val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    @Override
    public int hashCode() {
        return currentHashValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Hasher)) return false;
        return currentHashValue == ((Hasher) obj).currentHashValue;
    }

    @Override
    public String toString() {
        return "Hasher{" + currentHashValue + "}";
    }
}
