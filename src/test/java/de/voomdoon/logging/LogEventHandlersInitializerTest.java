package de.voomdoon.logging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.root.RootLogger;

/**
 * Test class for {@link LogEventHandlersInitializer}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
class LogEventHandlersInitializerTest {

	/**
	 * @author André Schulz
	 *
	 * @since 0.1.0
	 */
	private class TestClassLoader extends URLClassLoader {

		/**
		 * @param urls
		 * @since 0.1.0
		 */
		public TestClassLoader(URL[] urls) {
			super(urls);
		}

		/**
		 * @since 0.1.0
		 */
		@Override
		public Enumeration<URL> getResources(String name) throws IOException {
			if ("META-INF/services/de.voomdoon.logging.LogEventHandler".equals(name)) {
				String location = System.getProperty("user.dir")
						+ "/src/test/resources/META-INF_services/de.voomdoon.logging.LogEventHandler";

				return Collections.enumeration(List.of(new URL("file:/" + location)));
			}

			return super.getResources(name);
		}
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testInitialize_default_ConsoleLogEventHandler() {
		LogManager logManager = new LogManager();
		RootLogger rootLogger = mock(RootLogger.class);
		LogEventHandlersInitializer initializer = new LogEventHandlersInitializer(logManager, rootLogger);

		System.out.println("initialize...");

		PrintStream backup = System.out;

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			System.setOut(new PrintStream(out));
			initializer.initialize();

			logManager.getLoggerInternal(getClass()).info("test-message");

			assertThat(out.toString()).contains("test-message");
		} finally {
			System.setOut(backup);
		}
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testInitialize_withImplementation() {
		LogManager logManager = mock(LogManager.class);
		RootLogger rootLogger = mock(RootLogger.class);
		LogEventHandlersInitializer initializer = new LogEventHandlersInitializer(logManager, rootLogger);

		System.out.println("initialize...");
		initializer.initialize(new TestClassLoader(new URL[0]));

		verify(logManager).addLogEventHandlerInternal(any(LogEventHandler.class));
	}
}
