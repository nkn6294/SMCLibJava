package com.bkav.command.model.entity;

import com.bkav.command.model.StaticInputWordsModel;
import com.bkav.command.test.SampleData;
import com.bkav.home.data.HomeDevice;

public class HomeDeviceModel extends StaticInputWordsModel<HomeDevice> {
	
	public HomeDeviceModel() {
		super(SampleData.DEVICES);
		this.MODEL_NAME = "DEVICE";			
	}
	
	@Override
	protected HomeDevice getDataFromStringArray(String[] datas) {
		return HomeDevice.createFromStringArray(datas);
	}

}
