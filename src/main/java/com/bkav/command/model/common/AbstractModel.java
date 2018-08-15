package com.bkav.command.model.common;

import com.bkav.command.common.Model;
import com.bkav.command.common.ModelConfig;
import com.bkav.command.struct.result.ResultsProcess;

/***
 * Abstract for Model with model name.
 */
public abstract class AbstractModel implements Model {

	public AbstractModel() {
		this.modelName = "";
		this.modelConfig = new ModelConfig();
		this.init();
	}
	
	public AbstractModel(String modelName, Object...objects) {
		this.modelName = modelName;
		this.modelConfig = new ModelConfig();
		this.init();
	}

	@Override
	public String getModelName() {
		return this.modelName;
	}
	
	@Override
	public ModelConfig getModelConfig() {
		return this.modelConfig;
	}
	
	@Override
	public void setModelConfig(ModelConfig modeConfig) {
		this.modelConfig = modeConfig;
	}
	
	public String[] process(String[] input) {
    	return this.process(new ResultsProcess(input)).remains();
    }
	
	public ResultsProcess process(ResultsProcess input) {
    	return input;
    }

	
	protected String modelName = "MODEL";
	protected ModelConfig modelConfig;
	
	/***
	 * Init model name, model, data other.
	 */
	protected void init() {
	}
}
