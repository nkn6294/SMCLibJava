package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.bkav.command.data.CommonData;

public abstract class CollectionModel<T extends CommonData> extends CommonModel<T>
		implements Iterable<CommonModel<? extends CommonData>> {

	@Override
	public void test(String[]... commands) {
		this.models.forEach(item -> item.test(commands));
	}

	@Override
	public Iterator<CommonModel<? extends CommonData>> iterator() {
		return this.models.iterator();
	}

	/***
	 * Init collection models.
	 */
	protected void initModels() {
	}

	/***
	 * Create collection contain models.
	 * 
	 * @return
	 */
	protected Collection<CommonModel<? extends CommonData>> createCollectionModels() {
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

	private Collection<CommonModel<? extends CommonData>> models;
}