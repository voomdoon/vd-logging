package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import de.voomdoon.logging.logger.DefaultLoggerTest;
import de.voomdoon.logging.root.RootLogger;

/**
 * Abstract test class for {@link Logger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public abstract class LoggerTest {

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	private static class TestRootLogger implements RootLogger {

		/**
		 * @since 0.1.0
		 */
		@Override
		public void addLogEventHandler(LogEventHandler handler) {
			throw new UnsupportedOperationException("'addLogEventHandler' not implemented at 'TestRootLogger'!");
		}

		@Override
		public Set<String> getLogEventHanderNames() {
			throw new UnsupportedOperationException(
					"Method 'getLogEventHanderNames' not implemented at 'TestRootLogger'!");
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public void log(LogEvent logEvent) {
			EVENTS.add(logEvent);
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public void removeLogEventHandler(LogEventHandler handler) {
			throw new UnsupportedOperationException("'removeLogEventHandler' not implemented at 'TestRootLogger'!");
		}
	}

	/**
	 * @since 0.1.0
	 */
	protected static TestRootLogger ROOT_LOGGER = new TestRootLogger();

	/**
	 * @since 0.1.0
	 */
	private static final List<LogEvent> EVENTS = new ArrayList<>();

	/**
	 * @since 0.1.0
	 */
	protected Throwable error = new Throwable();

	/**
	 * @since 0.1.0
	 */
	protected Object message = new Object();

	/**
	 * @since 0.1.0
	 */
	private Logger logger;

	/**
	 * @param logger
	 *            {@link Logger}
	 * @since 0.1.0
	 */
	protected LoggerTest(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @since 0.1.0
	 */
	@AfterEach
	void afterEach() {
		EVENTS.clear();
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object() {
		logger.debug(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.DEBUG);
		assertThat(EVENTS.get(0).getMessage()).isEqualTo(message);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object_LogEvent_getLevel() {
		logger.debug(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.DEBUG);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object_LogEvent_getSourceClass() {
		logger.debug(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getSourceClass()).isEqualTo(DefaultLoggerTest.class);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object_LogEvent_getTimestamp() {
		long before = System.currentTimeMillis();
		logger.debug(message);
		long after = System.currentTimeMillis();

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getTimestamp()).isGreaterThanOrEqualTo(before).isLessThanOrEqualTo(after);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testDebug_Object_Throwable() {
		logger.debug(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.DEBUG);
		assertThat(EVENTS.get(0).getError()).isEqualTo(error);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testError_Object() {
		logger.error(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.ERROR);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testError_Object_Throwable() {
		logger.error(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.ERROR);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testFatal_Object() {
		logger.fatal(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.FATAL);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testFatal_Object_Throwable() {
		logger.fatal(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.FATAL);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testInfo_Object() {
		logger.info(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testInfo_Object_Throwable() {
		logger.info(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @param level
	 * @since 0.1.0
	 */
	@ParameterizedTest
	@EnumSource(LogLevel.class)
	void testIsActive_doesNotThrowAnything(LogLevel level) {
		assertDoesNotThrow(() -> logger.isActive(level));
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testLog_LogLevel_Object() {
		logger.log(LogLevel.INFO, message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testLog_LogLevel_Object_Throwable() {
		logger.log(LogLevel.INFO, message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.INFO);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testTrace_Object() {
		logger.trace(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.TRACE);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testTrace_Object_Throwable() {
		logger.trace(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.TRACE);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testWarn_Object() {
		logger.warn(message);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.WARN);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testWarn_Object_Throwable() {
		logger.warn(message, error);

		assertThat(EVENTS).hasSize(1);
		assertThat(EVENTS.get(0).getLevel()).isEqualTo(LogLevel.WARN);
	}
}