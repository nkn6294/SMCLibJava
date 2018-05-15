package com.bkav.command.model;

import com.bkav.command.common.Model;

public class DayInWeekModel implements Model {
    public final static String[] DAY_IN_WEEK = {
            "thu hai",
            "thu ba",
            "thu tu",
            "thu nam",
            "thu sau",
            "thu bay",
            "chu nhat"
    };

    @Override
    public String getModelName() {
        return MODEL_NAME;
    }

    @Override
    public String getModelValue() {
        return null;
    }

    public static final String MODEL_NAME = "DAY_IN_WEEK";

}
