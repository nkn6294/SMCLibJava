package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.bkav.command.common.Model;
import com.bkav.command.struct.ResultsProcess;

/***
 * Collection with order using for model process.
 *
 */
public class PipeLineModel implements Iterable<Model> {

	public PipeLineModel() {
		this.models = new LinkedList<>();
	}

	public PipeLineModel(Model... models) {
		this.models = new LinkedList<>();
		for (Model model : models) {
			this.models.add(model);
		}
	}

	public void addLast(Model model) {
		this.models.addLast(model);
	}

	public void addLast(Model... models) {
		for (Model model : models) {
			this.models.addLast(model);
		}
	}

	public void addFirst(Model... models) {
		for (Model model : models) {
			this.models.addFirst(model);
		}
	}

	public String[] getModelsString() {
		String[] names = new String[this.models.size()];
		for (int index = 0; index < this.models.size(); index++) {
			names[index] = this.models.get(index).getModelName();
		}
		return names;
	}

	public ResultsProcess process(ResultsProcess input) {
		for (int index = 0; index < this.models.size(); index++) {
			input = this.models.get(index).process(input);
		}
		return input;
	}
	public Collection<Iterator<Model>> getPermutation() {
		List<Iterator<Model>> list = new ArrayList<>();
		list.add(this.models.iterator());
		//TODO getPermutation collection;
		return list;
	}
	public void addFirst(Model model) {
		this.models.addFirst(model);
	}

	public void clear() {
		this.models.clear();
	}

	public void remove(Model model) {
		this.models.remove(model);
	}

	@Override
	public Iterator<Model> iterator() {
		return this.models.iterator();
	}

	private LinkedList<Model> models;

}
