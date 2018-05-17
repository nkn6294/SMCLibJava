package com.bkav.command.model.entity;

import org.junit.Before;

import com.bkav.command.model.CommonModel;
import com.bkav.command.model.ModelTest;
import com.bkav.command.test.SampleData;
import com.bkav.util.CollectionUtil;

public class EntityModelTest extends ModelTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.commands = CollectionUtil.convert(SampleData.SampleCommands2);
	}
	
	@Override
	protected CommonModel<?> createModel() {
		return new HomeEntityModel(); 
	}
}
