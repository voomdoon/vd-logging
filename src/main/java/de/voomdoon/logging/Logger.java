package de.voomdoon.logging;

/**
 * Logs messages at different {@link LogLevel}s.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public interface Logger {

	/**
	 * Logs a debug message.
	 *
	 * @param message
	 *            The message to log
	 * @since 0.1.0
	 */
	void debug(Object message);

	/**
	 * Logs a debug message with an error.
	 *
	 * @param message
	 *            The message to log
	 * @param throwable
	 *            DOCME
	 * @since 0.1.0
	 */
	void debug(Object message, Throwable throwable);

	/**
	 * Logs an error message.
	 *
	 * @param message
	 *            DOCME
	 * @since 0.1.0
	 */
	void error(Object message);

	/**
	 * Logs an error message with an error.
	 *
	 * @param message
	 *            DOCME
	 * @param throwable
	 *            DOCME
	 * @since 0.1.0
	 */
	void error(Object message, Throwable throwable);

	/**
	 * Logs a fatal message.
	 *
	 * @param message
	 *            DOCME
	 * @since 0.1.0
	 */
	void fatal(Object message);

	/**
	 * Logs a fatal message with an error.
	 *
	 * @param message
	 *            DOCME
	 * @param throwable
	 *            DOCME
	 * @since 0.1.0
	 */
	void fatal(Object message, Throwable throwable);

	/**
	 * Logs an informational message.
	 *
	 * @param message
	 *            DOCME
	 * @since 0.1.0
	 */
	void info(Object message);

	/**
	 * Logs an informational message with an error.
	 *
	 * @param message
	 *            DOCME
	 * @param throwable
	 *            DOCME
	 * @since 0.1.0
	 */
	void info(Object message, Throwable throwable);

	/**
	 * Reports whether a log level is active.
	 *
	 * under development FEATURE #12: support isActive(LogLevel)
	 * 
	 * @param level
	 *            DOCME
	 * @return DOCME
	 * @since 0.1.0
	 */
	boolean isActive(LogLevel level);

	/**
	 * Logs a message at the supplied level.
	 *
	 * @param level
	 *            DOCME
	 * @param message
	 *            DOCME
	 * @since 0.1.0
	 */
	void log(LogLevel level, Object message);

	/**
	 * Logs a message and error at the supplied level.
	 *
	 * @param level
	 *            DOCME
	 * @param message
	 *            DOCME
	 * @param throwable
	 *            DOCME
	 * @since 0.1.0
	 */
	void log(LogLevel level, Object message, Throwable throwable);

	/**
	 * Logs a trace message.
	 *
	 * @param message
	 *            DOCME
	 * @since 0.1.0
	 */
	void trace(Object message);

	/**
	 * Logs a trace message with an error.
	 *
	 * @param message
	 *            DOCME
	 * @param throwable
	 *            DOCME
	 * @since 0.1.0
	 */
	void trace(Object message, Throwable throwable);

	/**
	 * Logs a warning message.
	 *
	 * @param message
	 *            DOCME
	 * @since 0.1.0
	 */
	void warn(Object message);

	/**
	 * Logs a warning message with an error.
	 *
	 * @param message
	 *            DOCME
	 * @param throwable
	 *            DOCME
	 * @since 0.1.0
	 */
	void warn(Object message, Throwable throwable);
}
