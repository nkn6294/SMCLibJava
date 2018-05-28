package com.bkav.command.model.control;

import com.bkav.command.model.CollectionModel;

public class SimpleControlModel extends CollectionModel {
	
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
		this.MODEL_NAME = "SIMPLE_CONTROL";
	}

	@Override
	protected void initModels() {
		super.initModels();
		this.addModels(new MinControlModel(), 
				new MaxControlModel());
	}
	
}