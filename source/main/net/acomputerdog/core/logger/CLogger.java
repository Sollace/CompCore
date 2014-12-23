package net.acomputerdog.core.logger;

import net.acomputerdog.core.java.Patterns;
import net.acomputerdog.core.time.StandardClock;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Customized logger.  Tagged with a name specified by the creator of the logger.
 * Sample output:
 * [Test Mod][INFO] This is CLogger.logInfo().
 * [UNKNOWN][FATAL] The chat monitor thread has died unexpectedly!
 * [TPSMonitor][WARNING] The game tick rate has dropped below 50%!
 * [Awesome Logger of Awesomeness!] I'm Awesome!
 */
public class CLogger {

    /**
     * Clock used by all CLogger instances to print date and time.
     */
    private static final StandardClock loggerClock = new StandardClock();

    /**
     * The PrintWriter used to write Exception output
     */
    private final PrintWriter exceptionLogger;

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
    private LogLevel minimumLogLevel;

    /**
     * The PrintStream that this CLogger outputs to.
     */
    private PrintStream loggerOutput;

    /**
     * Creates a new CLogger.
     * @param name The name of this CLogger
     */
    public CLogger(String name) {
        this(name, false, false);
    }

    /**
     * Creates a new CLogger
     *
     * @param name        The name of this CLogger
     * @param includeDate If true, date will be printed with log output
     * @param includeTime If true, time will be printed with log output
     */
    public CLogger(String name, boolean includeDate, boolean includeTime) {
        this(name, includeDate, includeTime, LogLevel.DEBUG);
    }

    /**
     * Crates a new CLogger.
     *
     * @param name The Name of this CLogger
     * @param includeDate Set to true to include the date in log messages.
     * @param includeTime Set to true to include the time in log messages.
     */
    public CLogger(String name, boolean includeDate, boolean includeTime, LogLevel minimumLevel) {
        this.name = (name == null ? "NULL" : name);
        this.includeDate = includeDate;
        this.includeTime = includeTime;
        this.minimumLogLevel = minimumLevel;
        this.loggerOutput = System.out;
        this.exceptionLogger = new PrintWriter(new CLoggerWriter());
    }

    /**
     * Gets the date formatted for display, if enabled.
     *
     * @return Returns the date formatted for display, or "" if disabled.
     */
    private String getFormattedDate() {
        if (includeDate) {
            return "[" + loggerClock.getDateAsString() + "]";
        } else {
            return "";
        }
    }

    /**
     * Gets the time formatted for display, if enabled.
     *
     * @return Returns the time formatted for display, or "" if disabled.
     */
    private String getFormattedTime() {
        if (includeTime) {
            return "[" + loggerClock.getSimpleTimeAsString() + "]";
        } else {
            return "";
        }
    }

    /**
     * Prints out a message in the format [{name}]{message}/n.
     *
     * @param message The message to print.
     */
    private void log(String message) {
        loggerOutput.println(getFormattedDate() + getFormattedTime() + "[" + name + "]" + message);
    }


    /**
     * Prints out a message in the format [{name}] {message}/n.  This will always log, irregardless of the minium log level.
     *
     * @param message The message to print.
     */
    public void logRaw(String message) {
        log(message);
    }

    /**
     * Logs a message with the specified LogLevel, if that level is >= this logger's minimum level
     *
     * @param level   The level to log at.
     * @param message The message to log.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean log(LogLevel level, String message) {
        if (level.isAllowedBy(minimumLogLevel)) {
            log("[" + level.getLevelName() + "] " + message);
            return true;
        }
        return false;
    }

    /**
     * Prints out a message in the format [{name}][DEBUG] {message}/n, if Debug logging is allowed.
     *
     * @param message The message to print.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logDebug(String message) {
        return log(LogLevel.DEBUG, message);
    }

    /**
     * Prints out a message in the format [{name}][DETAIL] {message}/n, if Detail logging is enabled.
     *
     * @param message The message to print.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logDetail(String message) {
        return log(LogLevel.DETAIL, message);
    }

    /**
     * Prints out a message in the format [{name}][INFO] {message}/n, if Info logging is enabled.
     *
     * @param message The message to print.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logInfo(String message) {
        return log(LogLevel.INFO, message);
    }

    /**
     * Prints out a message in the format [{name}][WARNING] {message}/n, if Warning logging is enabled.
     *
     * @param message The message to print.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logWarning(String message, Throwable... throwables) {
        if (log(LogLevel.WARNING, message)) {
            logThrowables(throwables);
            return true;
        }
        return false;
    }

    /**
     * Prints out a message in the format [{name}][ERROR] {message}/n, if Error logging is enabled.
     *
     * @param message The message to print.
     * @param throwables       Optional parameter. Exception that has occurred
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logError(String message, Throwable... throwables) {
        if (log(LogLevel.ERROR, message)) {
            logThrowables(throwables);
            return true;
        }
        return false;
    }

    /**
     * Prints out a message in the format [{name}][FATAL] {message}/n, if Fatal logging is enabled.
     *
     * @param message The message to print.
     * @param throwables       Optional parameter. Exception that has occurred
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logFatal(String message, Throwable... throwables) {
        if (log(LogLevel.FATAL, message)) {
            logThrowables(throwables);
            return true;
        }
        return false;
    }

    /**
     * Logs a message with the [STACK] log level.  This level will always print, if the level calling it is allowed.
     * @param message The message to print.
     */
    private void logStack(String message) {
        log(LogLevel.STACK, message);
    }

    /**
     * Logs one or more Throwables with the [STACK] log level
     * @param throwables The throwables to log
     */
    private void logThrowables(Throwable... throwables) {
        for (Throwable t : throwables) {
            t.printStackTrace(exceptionLogger);
        }
    }

    /**
     * Checks if this logger will include the date
     * @return Return true if this logger will include the date.
     */
    public boolean doesIncludeDate() {
        return includeDate;
    }

    /**
     * Sets if this logger will include the date.
     * @param includeDate  If true, this logger will include the date.  If false, it will not.
     */
    public void setIncludeDate(boolean includeDate) {
        this.includeDate = includeDate;
    }

    /**
     * Checks if this logger will include the time
     * @return Return true if this logger will include the time.
     */
    public boolean doesIncludeTime() {
        return includeTime;
    }

    /**
     * Sets if this logger will include the time.
     * @param includeTime  If true, this logger will include the time.  If false, it will not.
     */
    public void setIncludeTime(boolean includeTime) {
        this.includeTime = includeTime;
    }

    /**
     * Gets the minimum logging level of this CLogger.  LogLevels below this amount will be hidden.
     * @return Return the LogLevel of this CLogger.
     */
    public LogLevel getMinimumLogLevel() {
        return minimumLogLevel;
    }

    /**
     * Sets the minimum logging level of this CLogger.  LogLevels below this amount will be hidden.
     * @param minimumLogLevel The minimum LogLevel to set
     */
    public void setMinimumLogLevel(LogLevel minimumLogLevel) {
        this.minimumLogLevel = minimumLogLevel;
    }

    /**
     * Gets the name of this CLogger
     * @return Return the name of this CLogger
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the PrintStream that this CLogger prints to.
     *
     * @return Return the PrintStream that this CLogger prints to.
     */
    public PrintStream getLoggerOutput() {
        return loggerOutput;
    }

    /**
     * Sets the PrintStream that this CLogger will print to.
     * @param loggerOutput The PrintStream that this CLogger will output to.  Cannot be null.
     */
    public void setLoggerOutput(PrintStream loggerOutput) {
        if (loggerOutput == null) {
            throw new IllegalArgumentException("Logger output cannot be null!");
        }
        this.loggerOutput = loggerOutput;
    }

    /**
     * Writer that outputs to the [STACK] LogLevel of this CLogger.  Will print out one item per line, skipping empty lines.  Used for printing Exceptions
     */
    private class CLoggerWriter extends Writer {

        /**
         * Creates a new CLoggerWriter
         */
        private CLoggerWriter() {
            super();
        }

        @Override
        /**
         * Writes the passed data to the [STACK] LogLevel of this CLogger, skipping empty lines.
         */
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
            //Does nothing
        }

        @Override
        public void close() throws IOException {
            //Does nothing
        }
    }
}
