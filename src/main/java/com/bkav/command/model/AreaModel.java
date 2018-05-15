package com.bkav.command.model;

import com.bkav.command.common.Model;

public class AreaModel implements Model {

    public final static String[] AREAS = {
            "phong khach",
            "phong ngu",
            "phong tam",
            "phong an",
            "hanh lang",
            "bep",
            "phong chinh",
            "phong hop",
    };

    @Override
    public String getModelName() {
        return MODEL_NAME;
    }

    public static final String MODEL_NAME = "AREA";
}
