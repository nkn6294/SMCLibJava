package com.bkav.command.model.time;

import java.time.DayOfWeek;

import com.bkav.command.model.ParseStringModel;

public class DayOfWeekModel extends ParseStringModel<DayOfWeek> {

	public DayOfWeekModel() {
		super();
		this.modelName = "DAY_OF_WEEK";
	}

	@Override
	protected boolean preWordFilter(String word) {		
		if (!super.preWordFilter(word)) {
			return false;
		}
		return word.startsWith("_e(");
	}
	@Override
	protected String getStringData(String word) {
		return word.replaceFirst("_e(\\d)", "$1");
	}
	@Override
	protected DayOfWeek createData(String word) {
		try {
			return DayOfWeek.of((Integer.parseInt(word) - 1));			
		} catch(Exception ex) {
			return null;
		}
	}
}
