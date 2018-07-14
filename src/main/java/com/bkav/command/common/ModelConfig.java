package com.bkav.command.common;

public class ModelConfig {
	
	public ModelConfig() {
		this.modelProcessMode = ModelProcessMode.PROCESS_AND_MARKED;
	}
	
	public ModelProcessMode getModelProcessMode() {
		return this.modelProcessMode;
	}
	
	public void setModelProcessMode(ModelProcessMode modelProcessMode) {
		this.modelProcessMode = modelProcessMode;
	}
	
	protected ModelProcessMode modelProcessMode;
}
