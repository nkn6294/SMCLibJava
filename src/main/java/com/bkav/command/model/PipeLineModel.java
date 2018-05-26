package com.bkav.command.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

import com.bkav.command.common.Model;
import com.bkav.struct.ResultsProcess;

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
		Arrays.stream(models).forEach(this.models::add);
	}

	public void addLast(Model model) {
		this.models.addLast(model);
	}

	public void addLast(Model... models) {
		Arrays.stream(models).forEach(this.models::addLast);
	}

	public void addFirst(Model... models) {
		Arrays.stream(models).forEach(this.models::addFirst);
	}

	public String[] getModelsString() {
		return this.models.stream().map(Model::getModelName).toArray(String[]::new);
	}

	public ResultsProcess process(ResultsProcess input) {
		for (int index = 0; index < this.models.size(); index++) {
			input = this.models.get(index).process(input);
		}
		return input;
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

	public Stream<Model> stream() {
		return this.models.stream();
	}

	@Override
	public Iterator<Model> iterator() {
		return this.models.iterator();
	}

	private LinkedList<Model> models;

}
