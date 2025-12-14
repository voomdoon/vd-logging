package de.voomdoon.logging.logger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.LogLevel;

/**
 * Test class for {@link LogEventImpl}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class LogEventImplTest {

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class ToStringTest {

		/**
		 * @since 0.1.0
		 */
		private LogEventImpl logEvent = new LogEventImpl(LogLevel.DEBUG, "test-message");

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_level() {
			String actual = logEvent.toString();

			assertThat(actual).contains("level: DEBUG");
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_message() {
			String actual = logEvent.toString();

			assertThat(actual).contains("message: test-message");
		}
	}
}
