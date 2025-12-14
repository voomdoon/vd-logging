package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import de.voomdoon.logging.test.TestLogEventHandler;
import de.voomdoon.logging.test.TestLogEventHandler2;

/**
 * Test class for {@link LogManager}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class LogManagerTest {

	/**
	 * Test method for {@link LogManager#addLogEventHandler(LogEventHandler)}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class AddLogEventHandler {

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_error_NullPointerException_handler_null() {
			assertThatThrownBy(() -> LogManager.addLogEventHandler(null)).isInstanceOf(NullPointerException.class)
					.hasMessageContaining("handler");
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_one() {
			TestLogEventHandler handler = new TestLogEventHandler();

			LogManager.addLogEventHandler(handler);

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test");

			assertThat(handler.getEvents()).hasSize(1);
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_same() {
			TestLogEventHandler handler = new TestLogEventHandler();

			LogManager.addLogEventHandler(handler);
			LogManager.addLogEventHandler(handler);

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test");

			assertThat(handler.getEvents()).hasSize(1);
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_two() {
			TestLogEventHandler handler1 = new TestLogEventHandler();
			TestLogEventHandler handler2 = new TestLogEventHandler();

			LogManager.addLogEventHandler(handler1);
			LogManager.addLogEventHandler(handler2);

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test");

			assertThat(handler1.getEvents()).hasSize(1);
			assertThat(handler2.getEvents()).hasSize(1);
		}
	}

	/**
	 * Test class for {@link LogManager#getLogger(Class)}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class GetLogger_Class_Test {

		/**
		 * @since 0.1.0
		 */
		private PrintStream outOriginal;

		/**
		 * @since 0.1.0
		 */
		@AfterEach
		void afterEach() {
			System.setOut(outOriginal);
		}

		/**
		 * @since 0.1.0
		 */
		@BeforeEach
		void beforeEach() {
			outOriginal = System.out;
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test() {
			Logger actual = LogManager.getLogger(LogManagerTest.class);

			assertThat(actual).isNotNull();
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_logToConsole() {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			System.setOut(new PrintStream(out));

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test-message");

			assertThat(new String(out.toByteArray())).contains("test-message");
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_sameInstance() {
			Logger actual1 = LogManager.getLogger(LogManagerTest.class);
			Logger actual2 = LogManager.getLogger(LogManagerTest.class);

			assertThat(actual2).isSameAs(actual1);
		}
	}

	/**
	 * Test method for {@link LogManager#removeLogEventHandler(LogEventHandler)}.
	 *
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	@Nested
	class RemoveLogEventHandler {

		/**
		 * @since 0.1.0
		 */
		@Test
		void test() {
			TestLogEventHandler handler = new TestLogEventHandler();

			LogManager.addLogEventHandler(handler);
			LogManager.removeLogEventHandler(handler);

			Logger logger = LogManager.getLogger(getClass());
			logger.info("test");

			assertThat(handler.getEvents()).isEmpty();
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_error_NoSuchElementException() {
			TestLogEventHandler handler = new TestLogEventHandler();

			assertThatThrownBy(() -> LogManager.removeLogEventHandler(handler))
					.isInstanceOf(NoSuchElementException.class).hasMessageContaining("handler");
		}

		/**
		 * @since 0.1.0
		 */
		@Test
		void test_error_NullPointerException() {
			assertThatThrownBy(() -> LogManager.removeLogEventHandler(null)).isInstanceOf(NullPointerException.class)
					.hasMessageContaining("handler");
		}
	}

	/**
	 * @since 0.1.0
	 */
	@BeforeEach
	void beforeEach() {
		TestLogEventHandler.getInstances().clear();
		TestLogEventHandler2.getInstances().clear();
	}
}
