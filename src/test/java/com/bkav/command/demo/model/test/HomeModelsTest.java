package com.bkav.command.demo.model.test;

import com.bkav.command.demo.data.HomeTest;
import com.bkav.command.demo.model.HomeEntityModel;
import com.bkav.command.demo.model.control.ControlModel;
import com.bkav.command.model.ModelsTest;
import com.bkav.command.model.PipeLineModel;
import com.bkav.command.model.misc.AmountModel;
import com.bkav.command.model.time.TimeModel;

public class HomeModelsTest extends ModelsTest {

	@Override
	protected PipeLineModel createModels() {
		return new PipeLineModel(
				new HomeEntityModel(HomeTest.getHomeTest()), 
				new AmountModel(),
				new ControlModel(), 
				new TimeModel());
	}
}
