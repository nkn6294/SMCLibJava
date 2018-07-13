package com.bkav.command.model;

import com.bkav.command.common.Model;
import com.bkav.command.common.ModelConfig;

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

//	public void test(String[]... commands) {
//		Arrays.stream(commands)
//		.map(ResultsProcess::new)
//		.map(this::process)
//		.forEach(Object::toString);
//	}
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
	
	protected String modelName = "MODEL";
	protected ModelConfig modelConfig;
	
	/***
	 * Init model name, model, data other.
	 */
	protected void init() {
	}
}
