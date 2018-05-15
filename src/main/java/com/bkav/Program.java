package com.bkav;

import java.util.Arrays;

import com.bkav.command.common.Model;
import com.bkav.command.data.TextWrapper;
import com.bkav.command.model.ControlModel;
import com.bkav.command.model.DeviceModel;
import com.bkav.command.model.PipeLineModel;

public class Program {
	public static void main(String[] args) {
		int flag = 0;
		for (String[] output : DeviceModel.DEVICES_PROCESSED ) {
			String joined = Arrays.stream(output).reduce((result, element) -> result = result + "@" + element).get();
//			joined = String.join("@", output);
			System.out.println(joined);
		}
		
		if (flag == 0) {
			return;
		}
		PipeLineModel pipeLineModel = new PipeLineModel(new ControlModel(), new DeviceModel());
		String[] SampleCommands = {
			"bat den",
			"tat den"
		};
		String command = SampleCommands[0];
		TextWrapper textWrapper = new TextWrapper(command);
		for (Model model : TextWrapper.proccess(textWrapper, pipeLineModel)) {
			System.out.println(model);
		}
	}
}
