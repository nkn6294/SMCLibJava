package com.bkav.command.common;

import java.util.Collection;
import java.util.List;

import com.bkav.command.data.CommonData;
import com.bkav.struct.ResultsProcess;

public interface Model {
    public String getModelName();
    
    default public Collection<CommonData> getModelValue() {
    	return null;
    };
    
    public void test(String[]... datas);
    
    default public String[] process(String[] input) {
    	return input;
    };
    
    default public List<String> process(List<String> input) {
    	return input;
    };
    
    default public ResultsProcess process(ResultsProcess input) {
    	return input;
    };
}
