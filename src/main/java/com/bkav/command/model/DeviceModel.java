package com.bkav.command.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bkav.command.common.Model;
import com.bkav.command.test.SampleData;
import com.bkav.util.CollectionUtil;

public class DeviceModel implements Model {

	static {
		String[][] converts = CollectionUtil.convert(SampleData.DEVICES);
		Arrays.sort(converts, (strings1, strings2) -> {
			if (strings1.length == 0) return strings2.length == 0 ? 0 : -1;
			if (strings2.length == 0) return 1;
			
			return 1;
		});
		DEVICES_PROCESSED = converts;
	}
	
    public final static String[] DEVICES = {
            "rem 1",
            "rem 2",
            "den chum",
            "den chum bep",
            "den tranh",
            "den roi",
            "den hanh lang",
            "den roi bep",
            "den hat",
            "den roi trong",
            "den roi ngoai",
            "quat hut",
            "may chieu",
            "man chieu",
            "dieu hoa",
            "toc do gio",
            "huong gio",
            "rem ngoai",
            "rem trai ngoai",
            "rem trai trong",
            "rem phai",
            "binh nong lanh",
            "quat hut",
    };
    
    public final static String[][] DEVICES_PROCESSED;
    
    public String getModelName() {
        return MODEL_NAME;
    }

    @Override
	public String getModelValueString() {
		return this.value == null ? null : this.value.toString();
	}

	@Override
	public Object getModelValue() {
		return this.value;
	}

	@Override
	public String[] process(String[] input) {
		return (String[])this.process(Arrays.stream(input).collect(Collectors.toList())).toArray();
	}

	@Override
	public List<String> process(List<String> input) {
		List<String> output = new ArrayList<>();
		for (int index = 0; index < input.size(); index++) {
			
		}
		return output;
	}
	
	private Object value = null;
	private final static String MODEL_NAME = "DEVICE";
}
