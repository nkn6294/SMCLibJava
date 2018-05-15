package com.bkav.command.model;

import com.bkav.command.common.Model;

public class TimeUnitModel implements Model {
    public final static String[] TIME_UNITS = {
            "giay",
            "phut",
            "gio",
            "ngay",
            "tuan",
            "thang",
            "nam"
    };
    @Override
    public String getModelName() {
        return MODEL_NAME;
    }


    public static final String MODEL_NAME = "TIME";
}
