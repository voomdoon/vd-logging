package de.voomdoon.logging;

/**
 * Describes a logging event.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public interface LogEvent {

	/**
	 * Returns the associated error.
	 *
	 * @return {@link Throwable} or {@code null}
	 * @since 0.1.0
	 */
	Throwable getError();

	/**
	 * Returns the log level.
	 *
	 * @return {@link LogLevel}
	 * @since 0.1.0
	 */
	LogLevel getLevel();

	/**
	 * Returns the message.
	 *
	 * @return {@link Object}
	 * @since 0.1.0
	 */
	Object getMessage();

	/**
	 * Returns the source class.
	 *
	 * @return {@link Class}
	 * @since 0.1.0
	 */
	Class<?> getSourceClass();

	/**
	 * Returns the event timestamp.
	 *
	 * @return timestamp in milliseconds
	 * @since 0.1.0
	 */
	long getTimestamp();
}
