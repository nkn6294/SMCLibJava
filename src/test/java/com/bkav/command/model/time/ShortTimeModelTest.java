package com.bkav.command.model.time;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bkav.command.common.Model;
import com.bkav.command.data.SampleData;
import com.bkav.command.model.ModelTest;

public class ShortTimeModelTest extends ModelTest {

	

	@Test
	public final void testProcess() {
		
		assertTrue(true);
	}

	@Test
	public final void testProcessTime() {
		
		assertTrue(true);
	}

	@Override
	protected Model createModel() {
		return new ShortTimeModel();
	}

	@Override
	protected String[] getTestCommands() {
		return SampleData.SampleSchedule2;
	}
}
