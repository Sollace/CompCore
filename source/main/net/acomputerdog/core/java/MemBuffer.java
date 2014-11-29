package net.acomputerdog.core.java;

public class MemBuffer {
    private static final int DEFAULT_BUFFER_SIZE = 1000000;

    private static byte[] buffer = null;

    public static void createBuffer(int size) {
        if (!isAllocated()) {
            buffer = new byte[size];
        }
    }

    public static void createBuffer() {
        createBuffer(DEFAULT_BUFFER_SIZE);
    }

    public static void destroyBuffer() {
        buffer = null;
    }

    public static void freeBuffer() {
        destroyBuffer();
        System.gc();
    }

    public static void resetBuffer(int size) {
        freeBuffer();
        createBuffer(size);
    }

    public static void resetBuffer() {
        resetBuffer(DEFAULT_BUFFER_SIZE);
    }

    public static boolean isAllocated() {
        return buffer != null;
    }

    public static int getBufferSize() {
        return buffer == null ? 0 : buffer.length;
    }
}
