package com.bkav.command.model;

import com.bkav.command.common.Model;

public class FunctionModel implements Model {
    public static final String MODEL_NAME = "FUNCTION";
    public final static String[] FUNCTION = {
            "buoi sang",
            "buoi trua",
            "buoi chieu",
            "buoi toi",
            "buoi dem",
            "hop",
            "giai tri",
            "di ngu",
            "ra khoi nha",
            "xem phim",
            "tieu chuan",
            "tro ve",
            "kiem tra nha",
            "chao buoi sang",
            "tap the duc",
            "ngu trua",
            "tam dung xem phim",
            "bat den",
            "tat den",
            "dung bua",

    };
    @Override
    public String getModelName() {
        return MODEL_NAME;
    }

    @Override
    public String getModelValue() {
        return null;
    }

}
