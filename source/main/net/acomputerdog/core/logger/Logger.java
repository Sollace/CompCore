package net.acomputerdog.core.logger;

import java.io.PrintStream;

public interface Logger {
	
    /**
     * Prints out a message in the format [{name}] {message}/n.  This will always log, irregardless of the minium log level.
     *
     * @param message The message to print.
     */
    public void logRaw(String message);

    /**
     * Logs a message with the specified LogLevel, if that level is >= this logger's minimum level
     *
     * @param level   The level to log at.
     * @param message The message to log.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean log(LogLevel level, String message);

    /**
     * Prints out a message in the format [{name}][DEBUG] {message}/n, if Debug logging is allowed.
     *
     * @param message The message to print.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logDebug(String message);

    /**
     * Prints out a message in the format [{name}][DETAIL] {message}/n, if Detail logging is enabled.
     *
     * @param message The message to print.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logDetail(String message);

    /**
     * Prints out a message in the format [{name}][INFO] {message}/n, if Info logging is enabled.
     *
     * @param message The message to print.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logInfo(String message);

    /**
     * Prints out a message in the format [{name}][WARNING] {message}/n, if Warning logging is enabled.
     *
     * @param message The message to print.
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logWarning(String message, Throwable... throwables);

    /**
     * Prints out a message in the format [{name}][ERROR] {message}/n, if Error logging is enabled.
     *
     * @param message The message to print.
     * @param throwables       Optional parameter. Exception that has occurred
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logError(String message, Throwable... throwables);

    /**
     * Prints out a message in the format [{name}][FATAL] {message}/n, if Fatal logging is enabled.
     *
     * @param message The message to print.
     * @param throwables       Optional parameter. Exception that has occurred
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logFatal(String message, Throwable... throwables);
    
    /**
     * Prints out a message on the provided logging level followed by exceptions me
     *
     * @param message The message to print.
     * @param throwables       Optional parameter. Exception that has occurred
     * @return Return true if the logging as allowed, false otherwise
     */
    public boolean logExceptions(String message, LogLevel level, Throwable... throwables);
    
    /**
     * Checks if this logger will include the date
     * @return Return true if this logger will include the date.
     */
    public boolean doesIncludeDate();

    /**
     * Sets if this logger will include the date.
     * @param includeDate  If true, this logger will include the date.  If false, it will not.
     */
    public void setIncludeDate(boolean includeDate);

    /**
     * Checks if this logger will include the time
     * @return Return true if this logger will include the time.
     */
    public boolean doesIncludeTime();

    /**
     * Sets if this logger will include the time.
     * @param includeTime  If true, this logger will include the time.  If false, it will not.
     */
    public void setIncludeTime(boolean includeTime);
    
    /**
     * Gets the minimum logging level of this CLogger.
     * LogLevels below this amount will be hidden.
     * @return Return the LogLevel of this CLogger.
     */
    public LogLevel getMinimumLogLevel();

    /**
     * Sets the minimum logging level of this CLogger.
     * LogLevels below this amount will be hidden.
     * @param minimumLogLevel The minimum LogLevel to set
     */
    public void setMinimumLogLevel(LogLevel minimumLogLevel);

    /**
     * Gets the name of this CLogger
     * @return Return the name of this CLogger
     */
    public String getName();

    /**
     * Gets the PrintStream that this CLogger prints to.
     *
     * @return Return the PrintStream that this CLogger prints to.
     */
    public PrintStream getLoggerOutput();

    /**
     * Sets the PrintStream that this CLogger will print to.
     * @param loggerOutput The PrintStream that this CLogger will output to.  Cannot be null.
     */
    public void setLoggerOutput(PrintStream loggerOutput);
}
