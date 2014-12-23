package net.acomputerdog.core.java;

/**
 * A class used to create a "buffer" of allocated heap memory.
 */
public class MemBuffer {
    /**
     * Default amount of memory to buffer (1MB)
     */
    private static final int DEFAULT_BUFFER_SIZE = 1000000;

    /**
     * The actual buffer
     */
    private byte[] buffer = null;

    /**
     * Allocate a buffer of the specified size, if a buffer does not already exist
     *
     * @param size The size of the buffer
     */
    public void allocate(int size) {
        if (!isAllocated()) {
            buffer = new byte[size];
        }
    }

    /**
     * Allocate a buffer of 1MB
     */
    public void allocate() {
        allocate(DEFAULT_BUFFER_SIZE);
    }

    /**
     * Deallocates the buffer.  Does not automatically free memory, but does mark for GC
     */
    public void deallocate() {
        buffer = null;
    }

    /**
     * Deallocate the buffer and trigger full GC.
     */
    public void free() {
        deallocate();
        System.gc();
    }

    /**
     * Frees the buffer and reallocates a new one
     *
     * @param size The size of the new buffer
     */
    public void reset(int size) {
        free();
        allocate(size);
    }

    /**
     * Frees the buffer and reallocates one of 1MB
     */
    public void reset() {
        reset(DEFAULT_BUFFER_SIZE);
    }

    /**
     * Deallocates the buffer and reallocates a new one
     *
     * @param size The size of the new buffer
     */
    public void resetQuick(int size) {
        deallocate();
        allocate(size);
    }

    /**
     * Deallocates the buffer and reallocates on of 1MB
     */
    public void resetQuick() {
        resetQuick(DEFAULT_BUFFER_SIZE);
    }

    /**
     * Checks if this MemBuffer currently has an allocated buffer
     * @return Return true if this MemBuffer has an active buffer.
     */
    public boolean isAllocated() {
        return buffer != null;
    }

    /**
     * Gets the size of the allocated buffer (in bytes)
     * @return Return the size of the allocated buffer, or 0 if no buffer is allocated.
     */
    public int getBufferSize() {
        return buffer == null ? 0 : buffer.length;
    }
}
