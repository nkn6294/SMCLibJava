package com.bkav.command.model;

import com.bkav.command.common.Model;

public class ControlModel implements Model {
	public static final String MODEL_NAME = "CONTROL";
	
    @Override
    public String getModelName() {
        return MODEL_NAME;
    }

    @Override
	public String[] process(String[] input) {
		return Model.super.process(input);
	}

    public Object getModelValue() {
        return this.value;
    }
    
    private ControlModelValue value;
    
    private final static String[] MIN_CONTROL = {
    		"tat",
    		"nho hon",
    		"dong",
    		"nho nhat",    		
    };
    
    private final static String[] MAX_CONTROL = {
    		"bat",
    		"mo",
    		"kich hoat",
    		"lon nhat",
    };
    
    private final static String[] LOWER_CONTROL = {
    		"giam",
    		"bot",
    };
    
    private final static String[] UPPER_CONTROL = {
    		"tang",
    		"them",
    		"lon hon",
    };
    
    private final static String[] VALUE_CONTROL = {
    		
    };
}
