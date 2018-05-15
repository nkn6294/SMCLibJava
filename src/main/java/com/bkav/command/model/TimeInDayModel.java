package com.bkav.command.model;

import com.bkav.command.common.Model;

public class TimeInDayModel implements Model {

    public final static String[] TIME_IN_DAY = {
            "sang", // 10 GIO SANG
            "trua",
            "chieu",
            "toi"
    };

    @Override
    public String getModelName() {
        return MODEL_NAME;
    }


    public static final String MODEL_NAME = "TIME_IN_DAY";
}
