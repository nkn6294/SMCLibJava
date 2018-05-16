package com.bkav.command.model.control;

import com.bkav.command.data.Control;
import com.bkav.command.model.CollectionModel;

public class SimpleControlModel extends CollectionModel<Control> {
	
    public final static String[] MIN_CONTROL = {
    		"tat",
    		"nho hon",
    		"dong",
    		"nho nhat",    		
    };
    
    public final static String[] MAX_CONTROL = {
    		"bat",
    		"mo",
    		"kich hoat",
    		"lon nhat",
    };

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "SIMPLE_CONTROL";
	}

	
	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(new MinControlModel(), new MaxControlModel());
	}


	@Override
	protected Control createDataFromStringArray(String[] datas) {
		return Control.createFromStringArray(datas, TypeControl.NONE, 0);
	}
	
	
}