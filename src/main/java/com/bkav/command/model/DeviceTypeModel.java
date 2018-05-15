package com.bkav.command.model;

import com.bkav.command.common.Model;

public class DeviceTypeModel implements Model {

    public final static String[] DEVICE_TYPE = {
            "den", // LIGHT
            "rem", // CURTAIN
            "dieu hoa", // air conditioner // dieu hoa trong phong...(1 type /room)
            "cong tac", // SWITCH,
            "binh nong lanh",
            "tv",
    };

    public String getModelName() {
        return MODEL_NAME;
    }

    public String getModelValue() {
        return null;
    }

    private final static String MODEL_NAME = "DEVICE_TYPE";
}
