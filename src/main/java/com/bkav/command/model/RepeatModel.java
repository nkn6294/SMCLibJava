package com.bkav.command.model;

import com.bkav.command.common.Model;

public class RepeatModel implements Model {

    public final static String[] REPEATES = {
            "mot lan",//"gio nay ngay mai/tuan sau/...
            "hang ngay",
            "hang tuan",
            "hang thang",
            "hang nam",
    };


    @Override
    public String getModelName() {
        return MODEL_NAME;
    }

    public static final String MODEL_NAME = "REPEATE";
}
