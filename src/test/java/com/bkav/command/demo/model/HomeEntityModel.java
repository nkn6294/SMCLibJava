package com.bkav.command.demo.model;

import com.bkav.command.common.Model;
import com.bkav.command.demo.data.Home;
import com.bkav.command.model.CollectionModel;
import com.bkav.command.model.PipeLineModel;
import com.bkav.command.model.PipeLineModelSupplier;
import com.bkav.command.struct.ResultsProcess;

public class HomeEntityModel extends CollectionModel {

	public HomeEntityModel(Home home) {
		super(CollectionModel.CUSTOM_OPTIONAL);
		this.home = home;
		super.init();
	}
	protected Home home;
	@Override
	protected void init() {
		super.init();
		this.modelName = "ENTITY";
	}

	@Override
	protected void initModels() {
		super.initModels();
		if (this.home == null) {
			return;
		}
		this.addModels(
				new HomeAreaModel(this.home.areas()),
				new HomeEntityTypeModel(),
				new HomeDeviceModel(this.home.devices()),
				new HomeFunctionModel(this.home.functions()), 
				new HomeDeviceTypeModel(this.home.deviceTypes()));
	}
	@Override
	protected PipeLineModelSupplier createPipeLineModelSupplier(PipeLineModel pipeLineModel) {
		return new HomeEntityPipeLineModelSupplier(pipeLineModel);
	}
	
	protected class HomeEntityPipeLineModelSupplier extends PipeLineModelSupplier {

		public HomeEntityPipeLineModelSupplier(PipeLineModel pipeLine) {
			super(pipeLine);
		}
		
		@Override
		public Model get(ResultsProcess resultsProcess) {
			Model model = super.get(resultsProcess);
//			if (model == null) {
//				return model;
//			}
//			for (Object value : resultsProcess) {
//				if (this.entityType == null && value instanceof HomeEntityType) {
//					this.entityType = (HomeEntityType) value;
//				}
//				if (this.area == null && value instanceof HomeArea) {
//					this.area = (HomeArea)value;
//				}
//			}
//			if (this.entityType == null) {
//				return model;
//			}
			
			return model;
		}		
//		private HomeArea area = null;
//		private HomeEntityType entityType = null;
	}
}
