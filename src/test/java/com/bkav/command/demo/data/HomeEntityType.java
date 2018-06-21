package com.bkav.command.demo.data;

import com.bkav.command.data.CommonData;
import com.bkav.command.demo.model.EntityType;

public class HomeEntityType extends CommonData {
	public static HomeEntityType createFromStringArray(String[] datas, EntityType typeControl) {
		return new HomeEntityType(getSimpleName(datas), typeControl);
	}

	public HomeEntityType(String name, EntityType typeControl) {
		super(name);
		this.entityType = typeControl;
	}

	public HomeEntityType(String name) {
		this(name, EntityType.NONE);
	}

	public EntityType getEntityType() {
		return this.entityType;
	}

	@Override
	public String toString() {
		return String.format("%s [entityType=%s, id=%s, name=%s]", this.getClass().getSimpleName(),entityType, id, name);
	}

	private EntityType entityType;
}
