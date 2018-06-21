package com.bkav.command.demo.model.test;

import com.bkav.command.common.Model;
import com.bkav.command.data.SampleData;
import com.bkav.command.demo.model.HomeFunctionModel;

public class HomeFunctionModelTest extends EntityModelTest {

	@Override
	protected Model createModel() {
		return new HomeFunctionModel(SampleData.FUNCTION);
	}
}
