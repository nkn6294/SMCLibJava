package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bkav.command.common.Model;
import com.bkav.command.struct.ResultsProcess;

public abstract class CollectionModel extends AbstractModel implements Iterable<Model> {

	public static final int SEQUENTIAL_OPTIONAL = 1;
	public static final int PARALLEL_OPTIONAL = 2;
	/***
	 * Custom process mode with {@link PipeLineModelSupplier} generate next model from before result.
	 */
	public static final int CUSTOM_OPTIONAL = 3;
	
	public CollectionModel() {
		super();
	}
	public CollectionModel(int processOptional) {
		super();
		this.processOptional(processOptional);
	}
	@Override
	public ResultsProcess process(ResultsProcess input) {
		if (this.processOptional != CUSTOM_OPTIONAL) {
			for (Model model : this.pipeLineModel) {
				input = model.process(input);
			}			
		} else {
			PipeLineModelSupplier models = this.createPipeLineModelSupplier(this.pipeLineModel);
			if (models == null) {
				return input;
			}
			Model model = models.get(input);
			while (model != null) {
				input = model.process(input);
				model = models.get(input);
			}
			models.reset();
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
	
	public int processOptional() {
		return this.processOptional;
	}
	
	protected PipeLineModel pipeLineModel;
	protected int processOptional = SEQUENTIAL_OPTIONAL;
	
	protected void processOptional(int processOptional) {
		this.processOptional = processOptional;
	}
	/***
	 * Create instance {@link PipeLineModelSupplier} from {@link CollectionModel#pipeLineModel} using in 
	 * {@link CollectionModel#CUSTOM_OPTIONAL} mode. 
	 * Override and return new instance of {@link PipeLineModelSupplier} if need.
	 */
	protected PipeLineModelSupplier createPipeLineModelSupplier(PipeLineModel pipeLineModel) {
		return new PipeLineModelSupplier(pipeLineModel);
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
		for (Model model : newModels) {
			this.pipeLineModel.addLast(model);
		}
	}

	@SafeVarargs
	protected final void removeModels(Model... newModels) {
		for (Model model: newModels) {
			this.pipeLineModel.remove(model);
		}
	}

	protected final void cleanAllModel() {
		this.pipeLineModel.clear();
	}
	
	@Override
	protected void init() {
		super.init();
		this.modelName = "COLLECTION_MODEL";
		this.pipeLineModel = new PipeLineModel();
		this.initModels();
	}
}