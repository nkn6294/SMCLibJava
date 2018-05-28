package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.bkav.command.common.Model;
import com.bkav.struct.ResultsProcess;

public abstract class CollectionModel extends AbstractModel implements Iterable<Model> {

	public void test(String[]... commands) {
		this.models.stream().forEach(item -> item.test(commands));
	}
	public CollectionModel() {
		super();
	}
	
	@Override
	public ResultsProcess process(ResultsProcess input) {
		for (Model model : this.models) {
			input = model.process(input);
		}
		return input;
	}
	
	@Override
	public Iterator<Model> iterator() {
		return this.models.iterator();
	}

	/***
	 * Init collection models and add to collection models.
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
	protected final void addModels(Model... newModels) {
		Arrays.stream(newModels).forEach(models::add);
	}

	@SafeVarargs
	protected final void removeModels(Model... newModels) {
		Arrays.stream(newModels).forEach(models::remove);
	}

	protected final void cleanAllModel() {
		this.models.clear();
	}

	@Override
	protected void init() {
		super.init();
		this.MODEL_NAME = "COLLECTION_MODEL";
		this.models = this.createCollectionModels();
		this.initModels();
	}

	private Collection<Model> models;
}