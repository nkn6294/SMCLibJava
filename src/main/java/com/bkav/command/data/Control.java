package com.bkav.command.data;

import com.bkav.command.model.control.TypeControl;

public class Control extends CommonData {
	public static Control createFromStringArray(String[] datas, TypeControl typeControl, int defaultValue) {
		return new Control(getSimpleName(datas), typeControl, defaultValue);
	}

	public Control(String name, TypeControl typeControl, int value) {
		super(name);
		this.value = value;
		this.typeControl = typeControl;
	}

	public Control(String name, TypeControl typeControl) {
		this(name, typeControl, -1);
	}

	public Control(String name) {
		this(name, TypeControl.NONE, -1);
	}

	public int getMinValue() {
		return minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public int setValue(int value) {
		switch (this.typeControl) {
		case LOWER:
			this.value -= value;
			break;
		case MAX:
			this.value += value;
			break;
		case MIN:
			this.value = this.minValue;
			break;
		case UPPER:
			this.value = this.maxValue;
			break;
		case VALUE:
			this.value = value;
			break;
		case NONE:
			// no change
			break;
		default:
			break;
		}
		if (this.value < this.minValue) {
			this.value = this.minValue;
		} else if (this.value > this.maxValue) {
			this.value = this.maxValue;
		}
		return this.value;
	}

	public TypeControl setTypeControl(TypeControl typeControl) {
		this.typeControl = typeControl;
		return this.typeControl;
	}

	public int getValue() {
		return this.value;
	}

	public TypeControl getTypeControl() {
		return this.typeControl;
	}

	@Override
	public String toString() {
		return String.format("Control [value=%s, typeControl=%s, minValue=%s, maxValue=%s, id=%s, name=%s]", value,
				typeControl, minValue, maxValue, id, name);
	}

	private int value;
	private TypeControl typeControl;
	private int minValue = 0;
	private int maxValue = 100;
}
