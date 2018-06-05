package com.bkav.command.model;

import com.bkav.command.common.Model;

/***
 * Abstract for Model with model name.
 */
public abstract class AbstractModel implements Model {

	public AbstractModel() {
		this.init();
	}
	
	public AbstractModel(String modelName, Object...objects) {
		this.modelName = modelName;
		this.init();
	}
	
	@Override
	public String getModelName() {
		return this.modelName;
	}
	
	protected String modelName = "MODEL";
	
	
	/***
	 * Init model name, model, data other.
	 */
	protected void init() {
	}
}
