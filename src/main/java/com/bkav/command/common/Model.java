package com.bkav.command.common;

import java.util.List;

public interface Model {
    public String getModelName();
    
    default public String getModelValueString() {
    	Object value = this.getModelValue();
    	return value == null ? null : value.toString();
    };
    
    default public Object getModelValue() {
    	return null;
    };
    
    default public String[] process(String[] input) {
    	return input;
    };
    
    default public List<String> process(List<String> input) {
    	return input;
    };
}
