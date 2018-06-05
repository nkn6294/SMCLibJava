package com.bkav.command.model.time;

import com.bkav.command.model.CollectionModel;

public class TimeModel extends CollectionModel { 

	@Override
	protected void init() {
		super.init();
		this.modelName = "TIME_MODEL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(
				new ShortTimeModel(),
				new DayInWeekModel(),
				new TimeInDayModel(),
				new TimeRepeatModel(),
				new TimeUnitModel());
	}

}
