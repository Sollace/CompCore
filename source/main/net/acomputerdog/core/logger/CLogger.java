package net.acomputerdog.core.logger;

import net.acomputerdog.core.java.Patterns;
import net.acomputerdog.core.time.StandardClock;

import java.io.*;

/**
 * Customized logger.  Tagged with a name specified by the creator of the logger.
 * Sample output:
 * [Test Mod][INFO] This is CLogger.logInfo().
 * [UNKNOWN][FATAL] The chat monitor thread has died unexpectedly!
 * [TPSMonitor][WARNING] The game tick rate has dropped below 50%!
 * [Awesome Logger of Awesomeness!] I'm Awesome!
 */
public class CLogger {
    private static StandardClock theClock = new StandardClock();

    /**
     * Name used to tag this logger's output.
     */
    private final String name;

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

    private PrintStream loggerOutput;

    private final PrintWriter exceptionLogger;

    /**
     * Creates a new CLogger.
     *
     * @param name The name of this CLogger
     */
    public CLogger(String name) {
        this(name, false, false);
    }

    public CLogger(String name, boolean includeDate, boolean includeTime) {
        this(name, includeDate, includeTime, ELogLevel.DEBUG);
    }

    /**
     * Crates a new CLogger.
     *
     * @param name       The Name of this CLogger
     * @param includeDate Set to true to include the date in log messages.
     * @param includeTime Set to true to include the time in log messages.
     */
    public CLogger(String name, boolean includeDate, boolean includeTime, ELogLevel minimumLevel) {
        this.name = (name == null ? "NULL" : name);
        this.includeDate = includeDate;
        this.includeTime = includeTime;
        this.minimumLogLevel = minimumLevel;
        this.loggerOutput = System.out;
        this.exceptionLogger = new PrintWriter(new CLoggerWriter());
    }

    public void log(ELogLevel level, String message) {
        if (level.isAllowed(minimumLogLevel)) {
            log("[" + level.getLevelName() + "] " + message);
        }
    }

    /**
     * Prints out a message in the format [{name}]{message}/n.
     *
     * @param message The message to print.
     */
    private void log(String message) {
        loggerOutput.println(getDate() + getTime() + "[" + name + "]" + message);
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
    public void logWarning(String message, Throwable... throwables) {
        log(ELogLevel.WARNING, message);
        logThrowables(throwables);
    }

    /**
     * Prints out a message in the format [{name}][ERROR] {message}/n.
     *
     * @param message The message to print.
     * @param throwables       Optional parameter. Exception that has occurred
     */
    public void logError(String message, Throwable... throwables) {
        log(ELogLevel.ERROR, message);
        logThrowables(throwables);
    }

    /**
     * Prints out a message in the format [{name}][FATAL] {message}/n.
     *
     * @param message The message to print.
     * @param throwables       Optional parameter. Exception that has occurred
     */
    public void logFatal(String message, Throwable... throwables) {
        log(ELogLevel.FATAL, message);
        logThrowables(throwables);
    }

    private void logStack(String message) {
        log(ELogLevel.STACK, message);
    }

    private void logThrowables(Throwable... throwables) {
        for (Throwable t : throwables) {
            t.printStackTrace(exceptionLogger);
            /*
            logStack(t.toString());
            StackTraceElement[] stack = t.getStackTrace();
            for (StackTraceElement element : stack) {
                logStack("\tat " + element.toString());
            }
            */
        }
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

    public String getName() {
        return name;
    }

    public OutputStream getLoggerOutput() {
        return loggerOutput;
    }

    public void setLoggerOutput(PrintStream loggerOutput) {
        if (loggerOutput == null) {
            throw new IllegalArgumentException("Logger output cannot be null!");
        }
        this.loggerOutput = loggerOutput;
    }

    private class CLoggerWriter extends Writer {

        private CLoggerWriter() {
            super();
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            StringBuilder builder = new StringBuilder();
            for (int index = off; index < off + len; index++) {
                char chr = cbuf[index];
                builder.append(chr);
            }
            if (builder.length() > 0) {
                String output = builder.toString();
                String[] outputs = output.split(Patterns.quote("\n"));
                for (String out : outputs) {
                    if (!(out.isEmpty() || out.trim().isEmpty())) {
                        logStack(out);
                    }
                }
            }
        }

        @Override
        public void flush() throws IOException {

        }

        @Override
        public void close() throws IOException {

        }
    }
}
