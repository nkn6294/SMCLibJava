package com.bkav.command.data.time;

import static org.junit.Assert.assertTrue;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.junit.Test;

import com.bkav.command.SystemManager;

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
		Period period = Period.hours(hour).plusMinutes(minute);
		
		TimeValue timeValue = new TimeValue(period);
		LocalTime time = timeValue.time();
		SystemManager.logger.info(time.toString());
		assertTrue(true);
	}

	@Test
	public void testMode() {
		assertTrue(true);
	}

}
