package com.bkav.command.common;

import com.bkav.command.struct.ResultsProcess;

public interface Model {
	/***
	 * Get name of model.
	 */
    public String getModelName();
    
    public String[] process(String[] input);
    
    /***
     * Process input with model and return new or update input model after process with this model.
     * @param input data input need process.
     * @return new/update {@link ResultsProcess} input.
     */
    public ResultsProcess process(ResultsProcess input);
    public ModelConfig getModelConfig();
    
    public void setModelConfig(ModelConfig mode);
}
