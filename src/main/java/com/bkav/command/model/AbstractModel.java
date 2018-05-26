package com.bkav.command.model;

import com.bkav.command.common.Model;

/***
 * Abstract for Model with model name.
 */
public abstract class AbstractModel implements Model {

	public AbstractModel() {
		this.init();
	}
	
	@Override
	public String getModelName() {
		return this.MODEL_NAME;
	}
	
	protected String MODEL_NAME = "ABSTRACT";
	/***
	 * Init model name, model, data other.
	 */
	protected void init() {
	}
}
