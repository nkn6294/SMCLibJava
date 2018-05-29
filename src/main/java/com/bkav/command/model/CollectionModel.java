package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bkav.command.common.Model;
import com.bkav.struct.ResultsProcess;

public abstract class CollectionModel extends AbstractModel implements Iterable<Model> {

	public void test(String[]... commands) {
		this.pipeLineModel.stream().forEach(item -> item.test(commands));
	}
	public CollectionModel() {
		super();
	}
	
	@Override
	public ResultsProcess process(ResultsProcess input) {
		//order
		for (Model model : this.pipeLineModel) {
			input = model.process(input);
		}
		return input;
	}
	/***
	 * Process input all model in collection with non conflic multimodel in same index.
	 * @param input
	 * @return
	 */
	public Collection<ResultsProcess> processAll(ResultsProcess input) {
		//TODO processALL collection Model -> not conflic
		List<ResultsProcess> results = new ArrayList<>();
		for (Model model : this.pipeLineModel) {
			if (model instanceof CollectionModel) {
				
				continue;
			} 
		}
		
		return results;
	}
	@Override
	public Iterator<Model> iterator() {
		return this.pipeLineModel.iterator();
	}

	public PipeLineModel pipeLineModel() {
		return this.pipeLineModel;
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
		Arrays.stream(newModels).forEach(this.pipeLineModel::addLast);
	}

	@SafeVarargs
	protected final void removeModels(Model... newModels) {
		Arrays.stream(newModels).forEach(this.pipeLineModel::remove);
	}

	protected final void cleanAllModel() {
		this.pipeLineModel.clear();
	}

	@Override
	protected void init() {
		super.init();
		this.MODEL_NAME = "COLLECTION_MODEL";
		this.pipeLineModel = new PipeLineModel();
		this.initModels();
	}

	protected PipeLineModel pipeLineModel;
}