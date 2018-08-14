package com.bkav.command.data.time;

import static org.junit.Assert.assertTrue;

import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.bkav.command.SMCManager;

public class TimeValueTest {

	@Test
	public void testLocalTime() {
		//TODO data.time.TimeValueTest
		assertTrue(true);
	}

	@Test
	public void testPeriod() {
		assertTrue(true);
	}

	@Test
	public void testTime() {
		int hour = 0;
		int minute = 30;
		Duration duration = Duration.standardHours(hour).plus(Duration.standardMinutes(minute));
		TimeValue timeValue = new TimeValue(duration);
		LocalTime time = timeValue.time();
		SMCManager.logger.info(time.toString());
		assertTrue(true);
	}

	@Test
	public void testMode() {
		assertTrue(true);
	}

}
