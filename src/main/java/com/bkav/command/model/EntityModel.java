package com.bkav.command.model;

import com.bkav.command.common.Model;

public class EntityModel implements Model {

    public static final String MODEL_NAME = "ENTITY";

    @Override
    public String getModelName() {
        return MODEL_NAME;
    }

    @Override
    public String getModelValue() {
        return null;
    }
}
