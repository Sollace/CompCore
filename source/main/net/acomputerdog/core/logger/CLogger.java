package net.acomputerdog.core.logger;

import net.acomputerdog.core.java.Patterns;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

import com.blazeloader.util.time.Clock;
import com.blazeloader.util.time.IClock;

/**
 * Customized logger.  Tagged with a name specified by the creator of the logger.
 * Sample output:
 * [Test Mod][INFO] This is CLogger.logInfo().
 * [UNKNOWN][FATAL] The chat monitor thread has died unexpectedly!
 * [TPSMonitor][WARNING] The game tick rate has dropped below 50%!
 * [Awesome Logger of Awesomeness!] I'm Awesome!
 */
public class CLogger implements Logger {
	
    /**
     * Default stream for new CLoggers
     */
    private static PrintStream defaultOut = System.out;
    
    /**
     * Gets the stream that will be used by new loggers
     *
     * @return return the default printStream
     */
    public static PrintStream getDefaultOut() {
        return defaultOut;
    }

    /**
     * Sets the stream that will be used by new loggers
     *
     * @param stream The new stream. Cannot be null.
     */
    public static void setDefaultOut(PrintStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Default stream cannot be null!");
        }
        defaultOut = stream;
    }
    
    private final PrintWriter exceptionOutput;
    private PrintStream loggerOutput;
	
    private final String name;
    
    private final IClock clock;
    private boolean includeDate;
    private boolean includeTime;
    
    private LogLevel minimumLogLevel;
    
    /**
     * Crates a new CLogger.
     *
     * @param name The Name of this CLogger
     * @param includeDate Set to true to include the date in log messages.
     * @param includeTime Set to true to include the time in log messages.
     * @param minimumLevel The minimum logging level for message to be output.
     */
    public CLogger(String name) {
        this(name, false, false);
    }
    
    /**
     * Crates a new CLogger.
     *
     * @param name The Name of this CLogger
     * @param includeDate Set to true to include the date in log messages.
     * @param includeTime Set to true to include the time in log messages.
     * @param minimumLevel The minimum logging level for message to be output.
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
     * @param minimumLevel The minimum logging level for message to be output.
     */
    public CLogger(String name, boolean includeDate, boolean includeTime, LogLevel minimumLevel) {
        this(name, includeDate, includeTime, minimumLevel, new Clock());
    }
    
    /**
     * Crates a new CLogger.
     *
     * @param name The Name of this CLogger
     * @param includeDate Set to true to include the date in log messages.
     * @param includeTime Set to true to include the time in log messages.
     * @param minimumLevel The minimum logging level for message to be output.
     * @param clock		A clock for formatting time messages
     */
    public CLogger(String name, boolean includeDate, boolean includeTime, LogLevel minimumLevel, IClock clock) {
        this.name = (name == null ? "NULL" : name);
        this.includeDate = includeDate;
        this.includeTime = includeTime;
        this.minimumLogLevel = minimumLevel;
        this.loggerOutput = defaultOut;
        this.exceptionOutput = new PrintWriter(new LogWriter());
        this.clock = clock;
    }
    
    private void logDirect(LogLevel level, String message) {
        loggerOutput.println(formatMessage(level, message));
    }
    
    /**
     * Formats a message by adding logger name, date, time, etc.
     *
     * @param message The message to log
     * @return Return the formatted string
     */
    protected String formatMessage(LogLevel level, String message) {
    	String result = "";
    	if (includeDate) result += getFormattedDate();
    	if (includeTime) result += getFormattedTime();
    	result += "[" + name + "] ";
    	if (level != null) result += "[" + level.getLevelName() + "]";
        return result + ": " + message;
    }
    
    /**
     * Prints out a message in the format [{name}] {message}/n.
     * This will always log, regardless of the minimum log level.
     *
     * @param message The message to print.
     */
    public void logRaw(String message) {
    	logDirect(null, message);
    }
    
    public boolean log(LogLevel level, String message) {
        if (level.isAllowedBy(getMinimumLogLevel())) {
        	logDirect(level, message);
            return true;
        }
        return false;
    }
    
    public boolean logDebug(String message) {
        return log(LogLevel.DEBUG, message);
    }
    
    public boolean logDetail(String message) {
        return log(LogLevel.DETAIL, message);
    }
    
    public boolean logInfo(String message) {
        return log(LogLevel.INFO, message);
    }
    
    public boolean logExceptions(String message, LogLevel level, Throwable... throwables) {
    	if (log(level, message)) {
    		for (Throwable t : throwables) {
                t.printStackTrace(exceptionOutput);
            }
            return true;
        }
        return false;
    }
    
    public boolean logWarning(String message, Throwable... throwables) {
        return logExceptions(message, LogLevel.WARNING, throwables);
    }
    
    public boolean logError(String message, Throwable... throwables) {
    	return logExceptions(message, LogLevel.ERROR, throwables);
    }
    
    public boolean logFatal(String message, Throwable... throwables) {
    	return logExceptions(message, LogLevel.FATAL, throwables);
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
    
    public LogLevel getMinimumLogLevel() {
        return minimumLogLevel;
    }
    
    public void setMinimumLogLevel(LogLevel minimumLogLevel) {
        this.minimumLogLevel = minimumLogLevel;
    }
    
    public String getName() {
        return name;
    }
    
    public PrintStream getLoggerOutput() {
        return loggerOutput;
    }
    
    public void setLoggerOutput(PrintStream loggerOutput) {
        if (loggerOutput == null) throw new IllegalArgumentException("Logger output cannot be null!");
        this.loggerOutput = loggerOutput;
    }
    
    /**
     * Gets the date formatted for display, if enabled.
     *
     * @return Returns the date formatted for display, or "" if disabled.
     */
    protected String getFormattedDate() {
		return "[" + clock.getDate() + "] ";
	}
    
    /**
     * Gets the time formatted for display, if enabled.
     *
     * @return Returns the time formatted for display, or "" if disabled.
     */
    protected String getFormattedTime() {
		return "[" + clock.getTime() + "] ";
	}
    
    /**
     * Writer that outputs to the [STACK] LogLevel of this Logger.
     * Will print out one item per line, skipping empty lines.
     * Used for printing Exceptions
     */
    private final class LogWriter extends Writer {
        private LogWriter() {
            super();
        }
        
        /**
         * Writes the passed data to the [STACK] LogLevel of this Logger, skipping empty lines.
         */
        public void write(char[] cbuf, int off, int len) throws IOException {
            StringBuilder builder = new StringBuilder();
            for (int index = off; index < off + len; index++) {
                builder.append(cbuf[index]);
            }
            if (builder.length() > 0) {
                String[] outputs = builder.toString().split(Patterns.LINE_DELIMITER);
                for (String out : outputs) {
                    if (!(out.isEmpty() || out.trim().isEmpty())) log(LogLevel.STACK, out);
                }
            }
        }
        
        public void flush() throws IOException {
        	loggerOutput.flush();
        }
        
        public void close() throws IOException { }
    }
}
