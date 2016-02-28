package net.acomputerdog.core.java;

/**
 * Thread sleep related utilities
 */
public class Sleep {
    /**
     * Syncs a method to a minimum duration.
     *
     * @param methodStartTime The value of System.currentTimeMillis() when the method was called.
     * @param duration        The minimum number of milliseconds for the method to take.
     */
    public static void sync(long methodStartTime, long duration) {
        sync(methodStartTime, duration, false);
    }

    /**
     * Syncs a method to a minimum duration.
     *
     * @param methodStartTime The value of System.currentTimeMillis() when the method was called.
     * @param duration        The minimum number of milliseconds for the method to take.
     * @param force           If true, InterruptedExceptions will be suppressed
     */
    public static void sync(long methodStartTime, long duration, boolean force) {
        if (duration < 10) duration = 10;
        long currTime = System.currentTimeMillis(); //only calculate current time once, otherwise math can be thrown off
        long endTime = duration + methodStartTime; //time that the method wants to end after
        if (endTime > currTime) { //if the method completed too fast, then sleep.
            sleep(endTime - currTime);
        }
    }

    /**
     * Makes the current thread sleep for the specified duration.  InterruptedExceptions will be suppressed.
     *
     * @param duration The duration to sleep for.
     */
    public static void sleep(long duration) {
        try {
            sleep(duration, true);
        } catch (InterruptedException ignored) {}
    }

    /**
     * Makes the current thread sleep for the specified duration. InterruptedExceptions will be suppressed if force is true.
     *
     * @param duration The duration to sleep for.
     * @param force    If true, InterruptedExceptions will be suppressed
     * @throws InterruptedException Throws InterruptedException if force is false, and the thread is interrupted.
     */
    public static void sleep(long duration, boolean force) throws InterruptedException {
        if (duration < 10) duration = 10;
        long endTime = System.currentTimeMillis() + duration; //time at which to wake up
        long sleep = duration;
        do {
            try {
                Thread.sleep(sleep); //sleep any time remaining from sleep duration
            } catch (InterruptedException e) {
                if (!force) throw e; //if force is true, suppress InterruptedException
            }
        } while (force && (sleep = endTime - System.currentTimeMillis()) > 0);  // if time is left and force is true, sleep for remaining time.
    }
}
