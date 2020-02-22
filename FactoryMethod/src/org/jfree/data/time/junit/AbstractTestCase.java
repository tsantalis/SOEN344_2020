package org.jfree.data.time.junit;


import junit.framework.TestCase;
import java.util.Locale;
import java.util.TimeZone;
import org.jfree.data.time.RegularTimePeriod;

public abstract class AbstractTestCase extends TestCase {
	public AbstractTestCase(String name) {
		super(name);
	}

	protected void testGetFirstMillisecond(long expected) {
		Locale saved = Locale.getDefault();
		Locale.setDefault(Locale.UK);
		TimeZone savedZone = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
		RegularTimePeriod period = getTimePeriod();
		assertEquals(expected, period.getFirstMillisecond());
		Locale.setDefault(saved);
		TimeZone.setDefault(savedZone);
	}
	
	public abstract RegularTimePeriod getTimePeriod();
}