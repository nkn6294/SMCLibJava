package com.bkav.command.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bkav.command.common.Model;
import com.bkav.command.common.TextProcesser;
import com.bkav.command.model.PipeLineModel;

public class TextWrapper {
	
	public static List<ResultProcess> proccess(TextWrapper text, PipeLineModel pipeLineModel) {
		List<ResultProcess> models = new ArrayList<>();
		String[] words = text.words();
		int currentLength = words.length;
		for (Model model : pipeLineModel) {
			if ((words = model.process(words)).length >= currentLength) {
				continue;
			}
			currentLength = words.length;
			models.add(new ResultProcess(model, words));
		}
		return models;
	}
	
	public TextWrapper(String text, TextProcesser textProcesser) {
		if (text == null || textProcesser == null) {
			throw new NullPointerException();
		}
		this.text = text;
		this.textProcesser = textProcesser;
		this.textProccessed = this.textProcesser.preProccessText(this.text);
		this.words = this.textProcesser.textToWords(this.text);
	}
	
	public TextWrapper(String text) {
		this(text, DefaultTextProcesser);
	}
	
	public String text() {
		return this.text;
	}
	
	public String textProccessed() {
		return this.textProccessed;
	}
	
	public String[] words() {
		return this.words;
	}
	
	public List<ResultProcess> proccess(PipeLineModel pipeLineModel) {
		return TextWrapper.proccess(this, pipeLineModel);
	}
	
	public static class ResultProcess {
		public ResultProcess(Model model, String[] remains ) {
			this.model = model;
			this.remains = remains;
		}
		
		public ResultProcess(Model model) {
			this(model, new String[] {});
		}
		
		public String[] getRemains() {
			return this.remains;
		}
		
		public void setRemains(String[] remains) {
			this.remains = remains;
		}
		
		public Model getModel() {
			return this.model;
		}
		
		@Override
		public String toString() {
			return String.format("%s [remains=%s, model=%s]", this.getClass().getSimpleName(), Arrays.toString(remains), model);
		}

		protected String[] remains;
		protected Model model;
	}
	
	private final static TextProcesser DefaultTextProcesser = new TextProcesser() {};
	private final String text;
	private final String textProccessed;
	private final String[] words;
	private final TextProcesser textProcesser;
}
