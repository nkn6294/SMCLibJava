package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.bkav.command.common.Model;
import com.bkav.command.data.CommonData;
import com.bkav.struct.ResultsProcess;

public abstract class CollectionModel<T extends CommonData> extends CommonModel<T>
		implements Iterable<Model> {

	@Override
	public void test(String[]... commands) {
		this.models.stream().filter(model -> model instanceof CommonModel<?>)
				.forEach(item -> ((CommonModel<?>)item).test(commands));
	}
	
	@Override
	public ResultsProcess process(ResultsProcess input) {
		for (Model model : this.models) {
			input = model.process(input);
		}
		return input;
	}
	
	@Override
	protected T getDataFromStringArray(String[] datas) {
		return null;
	}
	
	@Override
	public Iterator<Model> iterator() {
		return this.models.iterator();
	}

	/***
	 * Init collection models.
	 */
	protected void initModels() {
	}

	/***
	 * Create collection contain models.
	 */
	protected Collection<Model> createCollectionModels() {
		return new ArrayList<>();
	}

	@SafeVarargs
	protected final void addModels(CommonModel<? extends CommonData>... newModels) {
		Arrays.stream(newModels).forEach(models::add);
	}

	@SafeVarargs
	protected final void removeModels(CommonModel<? extends CommonData>... newModels) {
		Arrays.stream(newModels).forEach(models::remove);
	}

	protected final void cleanAllModel() {
		this.models.clear();
	}

	@Override
	protected void init() {
		super.init();
		MODEL_NAME = "COLLECTION_MODEL";
		this.models = this.createCollectionModels();
		this.initModels();
	}

	private Collection<Model> models;
}