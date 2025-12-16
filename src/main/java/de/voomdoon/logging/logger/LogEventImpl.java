package de.voomdoon.logging.logger;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogLevel;

/**
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class LogEventImpl implements LogEvent {

	/**
	 * @since 0.1.0
	 */
	private final Throwable error;

	/**
	 * @since 0.1.0
	 */
	private final LogLevel level;

	/**
	 * @since 0.1.0
	 */
	private final Object message;

	/**
	 * @since 0.1.0
	 */
	private final Class<?> sourceClass;

	/**
	 * @since 0.1.0
	 */
	private final long timestamp;

	/**
	 * @param level
	 *            {@link LogLevel}
	 * @param message
	 *            {@link Object}
	 * @since 0.1.0
	 */
	public LogEventImpl(LogLevel level, Object message) {
		this(level, message, null);
	}

	/**
	 * @param level
	 *            {@link LogLevel}
	 * @param message
	 *            {@link Object}
	 * @param error
	 *            optional {@link Throwable}
	 * @since 0.1.0
	 */
	public LogEventImpl(LogLevel level, Object message, Throwable error) {
		this(level, message, error, null);
	}

	/**
	 * DOCME add JavaDoc for constructor LogEventImpl
	 * 
	 * @param level
	 *            DOCME
	 * @param message
	 *            DOCME
	 * @param error
	 *            DOCME
	 * @param sourceClass
	 *            DOCME
	 * @since 0.2.0
	 */
	public LogEventImpl(LogLevel level, Object message, Throwable error, Class<?> sourceClass) {
		this.level = level;
		this.message = message;
		this.error = error;
		this.sourceClass = sourceClass;

		timestamp = System.currentTimeMillis();
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public Throwable getError() {
		return error;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public LogLevel getLevel() {
		return level;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public Object getMessage() {
		return message;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public Class<?> getSourceClass() {
		return sourceClass;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @since 0.1.0
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogEventImpl(timestamp: ");
		builder.append(timestamp);
		builder.append(", level: ");
		builder.append(level);
		builder.append(", message: ");
		builder.append(message);
		builder.append(", sourceClass: ");
		builder.append(sourceClass);
		builder.append(", error: ");
		builder.append(error);
		builder.append(")");
		return builder.toString();
	}
}
