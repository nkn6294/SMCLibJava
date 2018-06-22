package com.bkav.command.demo.model.test;

import com.bkav.command.common.Model;
import com.bkav.command.demo.SampleData;
import com.bkav.command.demo.model.HomeAreaModel;

public class HomeAreaModelTest extends EntityModelTest {

	@Override
	protected Model createModel() {
		return new HomeAreaModel(SampleData.AREAS);
	}
}
