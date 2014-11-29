package net.acomputerdog.core.hash;

public class Hasher {
    private int currentHashValue;

    public Hasher() {
        currentHashValue = Hash.SEED;
    }

    public Hasher hash(Object val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    public Hasher hash(int val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    public Hasher hash(byte val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    public Hasher hash(short val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    public Hasher hash(long val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    public Hasher hash(boolean val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    public Hasher hash(float val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

    public Hasher hash(double val) {
        currentHashValue = Hash.hash(currentHashValue, val);
        return this;
    }

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
