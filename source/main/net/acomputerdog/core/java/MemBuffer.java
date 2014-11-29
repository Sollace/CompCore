package net.acomputerdog.core.java;

public class MemBuffer {
    private static final int DEFAULT_BUFFER_SIZE = 1000000;

    private byte[] buffer = null;

    public void createBuffer(int size) {
        if (!isAllocated()) {
            buffer = new byte[size];
        }
    }

    public void createBuffer() {
        createBuffer(DEFAULT_BUFFER_SIZE);
    }

    public void destroyBuffer() {
        buffer = null;
    }

    public void freeBuffer() {
        destroyBuffer();
        System.gc();
    }

    public void resetBuffer(int size) {
        freeBuffer();
        createBuffer(size);
    }

    public void resetBuffer() {
        resetBuffer(DEFAULT_BUFFER_SIZE);
    }

    public boolean isAllocated() {
        return buffer != null;
    }

    public int getBufferSize() {
        return buffer == null ? 0 : buffer.length;
    }
}
