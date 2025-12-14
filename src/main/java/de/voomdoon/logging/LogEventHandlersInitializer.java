package de.voomdoon.logging;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

import de.voomdoon.logging.handler.ConsoleLogEventHandler;
import de.voomdoon.logging.logger.LogEventImpl;
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
	 * @since 0.1.0
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
			rootLogger.addLogEventHandler(new ConsoleLogEventHandler());// TESTME
			rootLogger.log(new LogEventImpl(LogLevel.INFO,
					"LogEventHandlersInitializer: no LogEventHandler found: added ConsoleLogEventHandler"));
		}
	}

	/**
	 * @param classLoader
	 *
	 * @param logManager
	 *
	 * @return {@link Set} of class names of added {@link LogEventHandler}s
	 * @since 0.1.0
	 */
	private Set<String> addLogEventHandlers(ClassLoader classLoader) {
		Set<String> result = new HashSet<>();

		ServiceLoader<LogEventHandler> loaders = ServiceLoader.load(LogEventHandler.class, classLoader);

		for (LogEventHandler handler : loaders) {
			logManager.addLogEventHandlerInternal(handler);// TESTME
			result.add(handler.getClass().getSimpleName());
		}

		return result;
	}
}