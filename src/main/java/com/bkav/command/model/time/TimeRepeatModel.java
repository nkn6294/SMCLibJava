package com.bkav.command.model.time;

import com.bkav.command.data.ModelData;
import com.bkav.command.data.time.TimeRepeat;
import com.bkav.command.data.time.TimeRepeat.TimeRepeatType;
import com.bkav.command.model.common.CollectionModel;
import com.bkav.command.model.common.StaticInputWordsModel;

public class TimeRepeatModel extends CollectionModel {

	@Override
	protected void init() {
		super.init();
		this.modelName = "TIME_REPEAT";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(
				new OnceTimeRepeatModel(),
				new DailyTimeRepeatModel(), 
				new WeeklyTimeRepeatModel(),
				new MonthlyTimeRepeatModel(), 
				new YearlyTimeRepeatModel());
	}

	class OnceTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

		public OnceTimeRepeatModel() {
			super(ModelData.ONCE_TIME_REPEAT);
			this.modelName = "ONCE_TIME_REPEAT";
		}

		@Override
		protected TimeRepeat getDataFromStringArray(String[] datas) {
			return TimeRepeat.createFromStringArray(datas, TimeRepeatType.ONCE);
		}
	}

	class DailyTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

		public DailyTimeRepeatModel() {
			super(ModelData.DAILY_TIME_REPEAT);
			this.modelName = "ONCE_TIME_REPEAT";
		}

		@Override
		protected TimeRepeat getDataFromStringArray(String[] datas) {
			return TimeRepeat.createFromStringArray(datas, TimeRepeatType.DAILY);
		}
	}

	class WeeklyTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

		public WeeklyTimeRepeatModel() {
			super(ModelData.WEEKLY_TIME_REPEAT);
			this.modelName = "WEEKLY_TIME_REPEAT";
		}

		@Override
		protected TimeRepeat getDataFromStringArray(String[] datas) {
			return TimeRepeat.createFromStringArray(datas, TimeRepeatType.WEEKLY);
		}
	}

	class MonthlyTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

		public MonthlyTimeRepeatModel() {
			super(ModelData.MONTHLY_TIME_REPEAT);
			this.modelName = "MONTHLY_TIME_REPEAT";
		}

		@Override
		protected TimeRepeat getDataFromStringArray(String[] datas) {
			return TimeRepeat.createFromStringArray(datas, TimeRepeatType.MONTHLY);
		}
	}

	class YearlyTimeRepeatModel extends StaticInputWordsModel<TimeRepeat> {

		public YearlyTimeRepeatModel() {
			super(ModelData.YEARLY_TIME_REPEAT);
			this.modelName = "YEARLY_TIME_REPEAT";
		}

		@Override
		protected TimeRepeat getDataFromStringArray(String[] datas) {
			return TimeRepeat.createFromStringArray(datas, TimeRepeatType.YEARLY);
		}
	}
}
