package de.voomdoon.logging.root;

import java.util.Set;

import de.voomdoon.logging.LogEvent;
import de.voomdoon.logging.LogEventHandler;

/**
 * Handles the logging internally.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public interface RootLogger {

	/**
	 * Adds a log event handler.
	 *
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	void addLogEventHandler(LogEventHandler handler);

	/**
	 * Returns the registered handler names.
	 *
	 * @return handler names as {@link Set}
	 * @since 0.1.0
	 */
	Set<String> getLogEventHandlerNames();

	/**
	 * Logs an event.
	 *
	 * @param logEvent
	 *            {@link LogEvent}
	 * @since 0.1.0
	 */
	void log(LogEvent logEvent);

	/**
	 * Removes a log event handler.
	 *
	 * @param handler
	 *            {@link LogEventHandler}
	 * @since 0.1.0
	 */
	void removeLogEventHandler(LogEventHandler handler);
}
