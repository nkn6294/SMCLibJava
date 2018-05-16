package com.bkav.command.test;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeProcess {
	public TimeProcess() {
	}

	public TimeProcess(String data, boolean after) {
		this();
		this.data = data;
		this.after = after;
		this.time = "00:00";
		this.process();
		this.validTime();
	}

	private void process() {
		if (this.processNormal()) {
			System.out.println("processnormal");
		} else if (this.processMore()) {
			System.out.println("processmore");
		} else {
			System.out.println("Not found");
		}
	}
	
	private void validTime() {
		String newTime = TimeProcess.validTime(this.getTime());
		if (this.after) {
			String[] string = newTime.split(":");
			int hour =  Integer.parseInt(string[0]);
			int minutes = Integer.parseInt(string[1]);
			Calendar calendar = Calendar.getInstance(new Locale("vi", "VN"));
			calendar.add(Calendar.HOUR, hour);
			calendar.add(Calendar.MINUTE, minutes);
			newTime = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
		}
		this.setTime(newTime);
	}

	private boolean processNormal() {
		Pattern pattern = Pattern.compile(TIME);
		Matcher matcher = pattern.matcher(this.data);
		if (matcher.find()) {
			this.setTime(matcher.group(0));
			return true;
		}
		return false;
	}

	private boolean processMore() {
		Pattern pattern = Pattern.compile(TIME_AFTER);
		Matcher matcher = pattern.matcher(this.data);
		String hour = "00";
		String minute = "00";
		if (matcher.find()) {
			if (matcher.group(1) != null && matcher.group(3) != null) {
				hour = matcher.group(1).split(" ")[0];
				minute = matcher.group(3).split(" ")[0];
			} else if (matcher.group(4) != null) {
				hour = matcher.group(4).split(" ")[0];
			} else if (matcher.group(5) != null) {
				minute = matcher.group(5).split(" ")[0];
			}
			this.setTime(hour + ":" + minute);
			return true;
		}
		return false;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static String validTime(String time) {
		String hour = time.split(":")[0];
		String minutes = time.split(":")[1];
		if (hour != null && minutes != null) {
			int hourValue = Integer.parseInt(hour);
			int minuteValue= Integer.parseInt(minutes);
			if (hourValue * 60 + minuteValue < 24 * 60) {
				hourValue = minuteValue / 60 + hourValue;
				minuteValue = minuteValue % 60;
				return String.format("%02d:%02d", hourValue, minuteValue); 
			}
		}
		return "00:00";
	}
	
	public static final String TIME = "([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
	public static final String TIME_AFTER = "(\\d+ giờ)(.*) (\\d+ phút)|(\\d+ giờ)|(\\d+ phút)";
	private String time;
	private String data;
	private boolean after;

}
