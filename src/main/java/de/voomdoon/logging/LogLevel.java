package de.voomdoon.logging;

/**
 * Severity of a {@link LogEvent}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public enum LogLevel {

	/**
	 * Debug level.
	 *
	 * @since 0.1.0
	 */
	DEBUG(1),

	/**
	 * Error level.
	 *
	 * @since 0.1.0
	 */
	ERROR(4),

	/**
	 * Fatal level.
	 *
	 * @since 0.1.0
	 */
	FATAL(5),

	/**
	 * Informational level.
	 *
	 * @since 0.1.0
	 */
	INFO(2),

	/**
	 * Trace level.
	 *
	 * @since 0.1.0
	 */
	TRACE(0),

	/**
	 * Warning level.
	 *
	 * @since 0.1.0
	 */
	WARN(3)

	;

	/**
	 * @since 0.1.0
	 */
	private int priority;

	/**
	 * @param priority
	 * @since 0.1.0
	 */
	private LogLevel(int order) {
		this.priority = order;
	}

	/**
	 * higher: more important
	 * 
	 * @return priority
	 * @since 0.1.0
	 */
	public int getPriority() {
		return priority;
	}
}
