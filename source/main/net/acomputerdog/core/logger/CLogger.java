package net.acomputerdog.core.logger;

import net.acomputerdog.core.time.StandardClock;

/**
 * Customized logger.  Tagged with a name specified by the creator of the logger.
 * Sample output:
 * [Test Mod][INFO] This is BLLogger.logInfo().
 * [UNKNOWN][FATAL] The chat monitor thread has died unexpectedly!
 * [TPSMonitor][WARNING] The game tick rate has dropped below 50%!
 * [Awesome Logger of Awesomeness!] I'm Awesome!
 */
public class CLogger {
    private static StandardClock theClock = new StandardClock();

    /**
     * Object that is the owner of this BLLogger.  Can be null.
     */
    private Object owner;

    /**
     * Name used to tag this logger's output.
     */
    private String name;

    /**
     * Include dates in debug messages?
     */
    private boolean includeDate;

    /**
     * Include time in debug messages?
     */
    private boolean includeTime;

    /**
     * The minimum logging level.
     */
    private ELogLevel minimumLogLevel;

    /**
     * Creates a new CLogger.
     *
     * @param owner The Object that created this CLogger.  Used to tag output.  Object.getClass().getSimpleName() will be used unless owner is one of:
     *              null -> "UNKNOWN"
     *              Mod -> Mod.getModName()
     *              String -> will use string
     */
    public CLogger(Object owner) {
        this(owner, false, false, ELogLevel.DEBUG);
    }

    /**
     * Crates a new BLLogger.
     *
     * @param owner       The Object that created this CLogger.  Used to tag output.  Object.getClass().getSimpleName() will be used unless owner is one of:
     *                    null -> "UNKNOWN"
     *                    Mod -> Mod.getModName()
     *                    String -> will use string
     * @param includeDate Set to true to include the date in log messages.
     * @param includeTime Set to true to include the time in log messages.
     */
    public CLogger(Object owner, boolean includeDate, boolean includeTime, ELogLevel minimumLevel) {
        this.owner = owner;
        if (owner == null) {
            name = "";
        } else if (owner instanceof String) {
            name = (String) owner;
        } else {
            name = owner.getClass().getSimpleName();
        }
        this.includeDate = includeDate;
        this.includeTime = includeTime;
    }

    public void log(ELogLevel level, String message) {
        if (level.isAllowed(minimumLogLevel)) {
            log(" [" + level.name() + "] " + message);
        }
    }

    /**
     * Prints out a message in the format [{name}]{message}/n.
     *
     * @param message The message to print.
     */
    private void log(String message) {
        System.out.println(getDate() + getTime() + "[" + name + "] " + message);
    }

    /**
     * Gets the date formatted for display, if enabled.
     *
     * @return Returns the date formatted for display, or "" if disabled.
     */
    private String getDate() {
        if (includeDate) {
            return "[" + theClock.getDateAsString() + "]";
        } else {
            return "";
        }
    }

    /**
     * Gets the time formatted for display, if enabled.
     *
     * @return Returns the time formatted for display, or "" if disabled.
     */
    private String getTime() {
        if (includeTime) {
            return "[" + theClock.getSimpleTimeAsString() + "]";
        } else {
            return "";
        }
    }

    /**
     * Prints out a message in the format [{name}] {message}/n.
     *
     * @param message The message to print.
     */
    public void logRaw(String message) {
        log(message);
    }

    /**
     * Prints out a message in the format [{name}][DEBUG] {message}/n.
     *
     * @param message The message to print.
     */
    public void logDebug(String message) {
        log(ELogLevel.DEBUG, message);
    }

    /**
     * Prints out a message in the format [{name}][DETAIL] {message}/n.
     *
     * @param message The message to print.
     */
    public void logDetail(String message) {
        log(ELogLevel.DETAIL, message);
    }

    /**
     * Prints out a message in the format [{name}][INFO] {message}/n.
     *
     * @param message The message to print.
     */
    public void logInfo(String message) {
        log(ELogLevel.INFO, message);
    }

    /**
     * Prints out a message in the format [{name}][WARNING] {message}/n.
     *
     * @param message The message to print.
     */
    public void logWarning(String message) {
        log(ELogLevel.WARNING, message);
    }

    /**
     * Prints out a message in the format [{name}][ERROR] {message}/n.
     *
     * @param message The message to print.
     * @param e       Optional parameter. Exception that has occured
     */
    public void logError(String message, Throwable... e) {
        log(ELogLevel.ERROR, message);
        logException(e);
    }

    /**
     * Prints out a message in the format [{name}][FATAL] {message}/n.
     *
     * @param message The message to print.
     * @param e       Optional parameter. Exception that has occured
     */
    public void logFatal(String message, Throwable... e) {
        log(ELogLevel.FATAL, message);
        logException(e);
    }

    private void logException(Throwable... e) {
        for (Throwable i : e) {
            log(i.getMessage() + "\n" + formatStackTrace(i.getStackTrace()));
        }
    }

    private String formatStackTrace(StackTraceElement[] stack) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < stack.length; index++) {
            StackTraceElement element = stack[index];
            builder.append(element.toString());
            if (index < stack.length - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public boolean doesIncludeDate() {
        return includeDate;
    }

    public void setIncludeDate(boolean includeDate) {
        this.includeDate = includeDate;
    }

    public boolean doesIncludeTime() {
        return includeTime;
    }

    public void setIncludeTime(boolean includeTime) {
        this.includeTime = includeTime;
    }

    public ELogLevel getMinimumLogLevel() {
        return minimumLogLevel;
    }

    public void setMinimumLogLevel(ELogLevel minimumLogLevel) {
        this.minimumLogLevel = minimumLogLevel;
    }
}
