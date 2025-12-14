package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.test.TestLogEvent;

/**
 * Abstract test class for {@link LogEventHandler}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public abstract class LogEventHandlerTest {

	/**
	 * @since 0.1.0
	 */
	protected LogEventHandler handler;

	/**
	 * @param handler
	 * @since 0.1.0
	 */
	protected LogEventHandlerTest(LogEventHandler handler) {
		this.handler = handler;
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent() {
		assertDoesNotThrow(
				() -> handler.handleLogEvent(new TestLogEvent().setLevel(LogLevel.INFO).setMessage("test-message")));
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testHandleLogEvent_error_NullPointerException_handler_null() {
		assertThatThrownBy(() -> handler.handleLogEvent(null)).isInstanceOf(NullPointerException.class)
				.hasMessageContaining("logEvent");
	}
}
