package com.bkav.command.common;

import com.bkav.command.common.active.result.BooleanResultCommandValue;

public interface CommandActiveAble extends CommandRequestModelWithType<BooleanResultCommandValue> {
	
//	public void active(Consumer<BooleanResultCommandValue> completedHandler);
	/*
	public void activateMode(String area, int modeIndex) {
		String request = String.format("<method service=\"home\" method=\"ActivateMode\" area=\"%s\" mode=\"%d\"/>",
				area, modeIndex);
		this.syncSession.writeText(request);

	}

	public void activateFunction(String area, int functionIndex, int duration, String password) {
		String request = String.format(
				"<method service=\"home\" method=\"ActivateFunction\" area=\"%s\" function=\"%d\" duration=\"%d\" password=\"%s\"/>",
				area, functionIndex, duration, password);
		this.syncSession.writeText(request);
	}

	public void changePassword(String oldPass, String newPass) {
		String request = String.format("<method service=\"home\" method=\"ChangePass\" oldPass=\"%s\" newPass=\"%s\"/>",
				oldPass, newPass);
		this.syncSession.writeText(request);
	}
*/
	/*
	public void callActive(DeviceItem deviceItem, int value) {
		String request = String.format(
				"<method service=\"device\" device=\"%s\" method=\"SetRegister\" register=\"%d\" value=\"%d\"/>",
				deviceItem.getName(), deviceItem.getSetRegister(), value);
		this.syncSession.writeText(request);
		logger.info(String.format("CALL ACTIVE: DEVICE SERVICE %s", request));
	}
	*/
	/*
	private void callActive() {
	String request = String.format("<method service=\"device\" device=\"%s\" method=\"SetRegister\" register=\"%d\" value=\"%d\"/>", this.deviceName, this.register, this.value);
	System.out.println("command..." + request);
	HomeCommand.syncSession.writeText(request);
	HomeCommand.logRecorder.write("DEVICE", "SERVICE", request);
}
*/
/*
private void callSchedule() {
	String valueString = String.format("<value register=\"%d\" value=\"%d\" power=\"%d\" />", this.register, this.value, this.power); 
	String scheduleInfo = String.format(SCHEDULE_FORMAT, this.beginTime, this.endTime, this.repeat, this.areaID, 0, valueString);
	String request = String.format("<method service=\"schedule\" method=\"Add\" device=\"%s\">%s</method>", this.deviceName, scheduleInfo);
	System.out.println("schedule.." + request);
	HomeCommand.syncSession.writeText(request);
	HomeCommand.logRecorder.write("DEVICE", "SERVICE", request);
}
*/
/*
private void callFunction(String areaID, int functionIndex) {
	String msg = String.format(
			"<method service=\"home\" method=\"ActivateFunction\" area=\"%s\" function=\"%s\" duration=\"-2\" password=\"\" source=\"voicecommand\"/>",
			areaID, functionIndex);
	HomeCommand.syncSession.writeText(msg);
	HomeCommand.logRecorder.write("CALL_FUNCTION", "VOICE_COMMAND", "function:" + command.functionName);
}
private final String SCHEDULE_FORMAT = "<item begin=\"%@\" end=\"%@\" enable=\"%d\" recurrent=\"%@\" areaid=\"%@\" sensor=\"%d\">%@</item>";
*/
}
