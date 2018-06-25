package com.bkav.command.data;

import com.bkav.command.model.misc.TypeAmount;

public class AmountData extends CommonData {
	public static AmountData createFromStringArray(String[] datas) {
		return new AmountData(getSimpleName(datas), TypeAmount.NONE);
	}

	public static AmountData createFromStringArray(String[] datas, TypeAmount type) {
		return new AmountData(getSimpleName(datas), type);
	}

	public AmountData(String name, TypeAmount type) {
		super(name);
		this.typeAmount = type;
	}

	public TypeAmount typeAmount() {
		return this.typeAmount;
	}

	@Override
	public String toString() {
		return String.format("%s [typeAmount=%s, id=%s, name=%s]", this.getClass().getSimpleName(), typeAmount, id, name);
	}

	protected TypeAmount typeAmount;
}