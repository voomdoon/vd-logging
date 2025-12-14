package de.voomdoon.logging.root;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.test.TestLogEvent;
import de.voomdoon.logging.test.TestLogEventHandler;

/**
 * Abstract class for {@link RootLogger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public abstract class RootLoggerTest {

	/**
	 * @since 0.1.0
	 */
	private RootLogger rootLogger;

	/**
	 * @param rootLogger
	 *            {@link RootLogger}
	 * @since 0.1.0
	 */
	public RootLoggerTest(RootLogger rootLogger) {
		this.rootLogger = rootLogger;
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testAddLogEventHandler_same() {
		TestLogEventHandler handler = new TestLogEventHandler();

		rootLogger.addLogEventHandler(handler);
		rootLogger.addLogEventHandler(handler);

		rootLogger.log(new TestLogEvent());

		assertThat(handler.getEvents()).hasSize(1);
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testGetLogEventHanderNames_className() {
		TestLogEventHandler handler = new TestLogEventHandler();

		rootLogger.addLogEventHandler(handler);

		assertThat(rootLogger.getLogEventHanderNames()).contains(handler.getClass().getSimpleName());
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testGetLogEventHanderNames_empty() {
		TestLogEventHandler handler = new TestLogEventHandler();

		rootLogger.addLogEventHandler(handler);
		rootLogger.removeLogEventHandler(handler);

		assertThat(rootLogger.getLogEventHanderNames()).isEmpty();
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testLog_LogEvent() {
		assertDoesNotThrow(() -> rootLogger.log(new TestLogEvent()));
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testRemoveLogEventHandler() {
		TestLogEventHandler handler = new TestLogEventHandler();

		rootLogger.addLogEventHandler(handler);
		rootLogger.removeLogEventHandler(handler);

		rootLogger.log(new TestLogEvent());

		assertThat(handler.getEvents()).isEmpty();
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testRemoveLogEventHandler_error_NoSuchElementException() {
		TestLogEventHandler handler = new TestLogEventHandler();

		assertThatThrownBy(() -> rootLogger.removeLogEventHandler(handler)).isInstanceOf(NoSuchElementException.class)
				.hasMessageContaining("handler");
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testRemoveLogEventHandler_error_NullPointerException() {
		try {
			rootLogger.removeLogEventHandler(null);
			fail("Missing 'NullPointerException'!");
		} catch (NullPointerException e) {
			assertThat(e.getMessage()).contains("handler");
		}
	}
}