package de.voomdoon.logging;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

import de.voomdoon.logging.handler.ConsoleLogEventHandler;
import de.voomdoon.logging.root.RootLogger;

/**
 * @since 0.1.0
 */
class LogEventHandlersInitializer {

	/**
	 * @since 0.1.0
	 */
	private LogManager logManager;

	/**
	 * @since 0.1.0
	 */
	private RootLogger rootLogger;

	/**
	 *
	 * @param rootLogger
	 * @since 0.1.0
	 */
	public LogEventHandlersInitializer(LogManager logManager, RootLogger rootLogger) {
		this.logManager = logManager;
		this.rootLogger = rootLogger;
	}

	/**
	 * DOCME add JavaDoc for method initialize
	 * 
	 * @since DOCME add inception version number
	 */
	void initialize() {
		initialize(LogEventHandler.class.getClassLoader());
	}

	/**
	 * @param classLoader
	 *            {@link ClassLoader}
	 * @since 0.1.0
	 */
	void initialize(ClassLoader classLoader) {
		Set<String> added = addLogEventHandlers(classLoader);

		if (added.isEmpty()) {
			System.out.println("LogEventHandlersInitializer: no LogEventHandler found: adding ConsoleLogEventHandler");
			rootLogger.addLogEventHandler(new ConsoleLogEventHandler());// TESTME
		}
	}

	/**
	 * @param classLoader
	 *
	 * @param logManager
	 *
	 * @return DOCME
	 * @since 0.1.0
	 */
	private Set<String> addLogEventHandlers(ClassLoader classLoader) {
		Set<String> result = new HashSet<>();

		ServiceLoader<LogEventHandler> loader = ServiceLoader.load(LogEventHandler.class, classLoader);

		for (LogEventHandler handler : loader) {
			logManager.addLogEventHandlerInternal(handler);// TESTME
			result.add(handler.getClass().getSimpleName());
		}

		return result;
	}
}
