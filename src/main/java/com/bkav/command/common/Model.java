package com.bkav.command.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bkav.command.struct.ResultsProcess;

public interface Model {
	/***
	 * Get name of model.
	 */
    public String getModelName();
    
    default public String[] process(String[] input) {
    	return this.process(new ResultsProcess(input)).remains();
    }
    
    default public List<String> process(List<String> input) {
    	String[] inputArray = input.toArray(new String[input.size()]);
    	String[] outputArray = this.process(inputArray);
    	return Arrays.stream(outputArray).collect(Collectors.toList());
    }
    /***
     * Process input with model and return new or update input model after process with this model.
     * @param input data input need process.
     * @return new/update {@link ResultsProcess} input.
     */
    default public ResultsProcess process(ResultsProcess input) {
    	return input;
    }
    
    default public ModelConfig getModelConfig() {
    	return new ModelConfig();
    }
    
    public void setModelConfig(ModelConfig mode);
}
