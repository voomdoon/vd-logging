package de.voomdoon.logging.root;

/**
 * {@link RootLoggerTest} for {@link SynchronousRootLogger}.
 *
 * @author André Schulz
 *
 * @since 0.1.0
 */
public class SynchronousRootLoggerTest extends RootLoggerTest {

	/**
	 * @param rootLogger
	 * @since 0.1.0
	 */
	public SynchronousRootLoggerTest() {
		super(new SynchronousRootLogger());
	}
}
