package de.voomdoon.logging;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.voomdoon.logging.handler.ConsoleLogEventHandler;
import de.voomdoon.logging.root.RootLogger;
import de.voomdoon.logging.test.TestLogEventHandler;

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

				try {
					return Collections.enumeration(List.of(new File(location).toURI().toURL()));
				} catch (Exception e) {
					throw new IOException("Error at 'getResources': " + e.getMessage(), e);
				}
			}

			return super.getResources(name);
		}
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testInitialize_default_ConsoleLogEventHandlerIsAdded() {
		LogManager logManager = new LogManager();
		RootLogger rootLogger = mock(RootLogger.class);
		LogEventHandlersInitializer initializer = new LogEventHandlersInitializer(logManager, rootLogger);

		initializer.initialize();

		verify(rootLogger).addLogEventHandler(any(ConsoleLogEventHandler.class));
	}

	/**
	 * @since 0.1.0
	 */
	@Test
	void testInitialize_withImplementation_configuredLogEventHandlerIsAdded() {
		LogManager logManager = mock(LogManager.class);
		RootLogger rootLogger = mock(RootLogger.class);
		LogEventHandlersInitializer initializer = new LogEventHandlersInitializer(logManager, rootLogger);

		initializer.initialize(new TestClassLoader(new URL[0]));

		verify(logManager).addLogEventHandlerInternal(any(TestLogEventHandler.class));
		verifyNoMoreInteractions(logManager);
	}
}