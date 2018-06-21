package com.bkav.command.demo.model;

import com.bkav.command.demo.data.HomeDevice;
import com.bkav.command.model.StaticInputWordsModel;

public class HomeDeviceModel extends StaticInputWordsModel<HomeDevice> {
	
	public HomeDeviceModel(String[] datas) {
		super(datas);
		this.modelName = "DEVICE";			
	}
	
	@Override
	protected HomeDevice getDataFromStringArray(String[] datas) {
		return HomeDevice.createFromStringArray(datas);
	}

}
