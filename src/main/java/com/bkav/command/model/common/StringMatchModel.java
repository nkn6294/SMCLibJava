package com.bkav.command.model.common;

import java.util.Collection;

import com.bkav.command.common.StringWithTypeData;
import com.bkav.command.util.StringUtil;

public class StringMatchModel extends StaticInputWordsModel<StringWithTypeData> {

	public static String defaultType() {
		return "";
	}

	public StringMatchModel(Collection<String> dataInput, String type) {
		super(dataInput);
		this.type = type;
	}

	public StringMatchModel(String[] dataInput, String type) {
		super(dataInput);
		this.type = type;
	}

	public StringMatchModel(Collection<String> dataInput) {
		super(dataInput);
		this.type = defaultType();
	}

	public StringMatchModel(String[] dataInput) {
		super(dataInput);
		this.type = defaultType();
	}

	public String type() {
		return this.type;
	}

	@Override
	protected StringWithTypeData getDataFromStringArray(String[] datas) {
		String data = this.createString(datas);
		StringWithTypeData stringData = this.creatStringWithData(data);
		return stringData;
	}

	protected String createString(String[] datas) {
		return StringUtil.joinString(datas);
	}

	protected StringWithTypeData creatStringWithData(String data) {
		return new StringWithTypeData(this.type, data);
	}

	protected String type;
}
