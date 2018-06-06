package com.bkav.command;

import java.util.Arrays;

import com.bkav.command.common.CommandTextProcesser;
import com.bkav.command.common.CommonCommandTextProcesser;
import com.bkav.command.common.TextProcesserAdvance;
import com.bkav.util.StringUtil;

public class SystemManager {
	
	static {
		TextProcesserAdvance textProcesserAdvance = new TextProcesserAdvance();
		textProcesserAdvance.addTextProcesser(String::toLowerCase);
		textProcesserAdvance.addTextProcesser(StringUtil::deAccentConvert);
		textProcesserAdvance.addTextProcesser(text -> text.replaceAll("đ", "d"));
		textProcesserAdvance.addTextProcesser(StringUtil::textToNumber);
		textProcesser = new CommonCommandTextProcesser(textProcesserAdvance);
	}
	
	public static final Logger logger = new Logger();
	public static final java.util.logging.Logger logger2 = java.util.logging.Logger.getLogger(System.class.getSimpleName());
	public static final CommandTextProcesser textProcesser;
	
	public static class Logger {
		public void info(String o) {
			System.out.println(o);
		}
		
		public void info(int o) {
			System.out.println(Integer.toString(o));
		}
		public void info(int[] os) {
			System.out.println(Arrays.toString(os));
		}
		public void info(long o) {
			System.out.println(Long.toString(o));
		}
		public void info(long[] os) {
			System.out.println(Arrays.toString(os));
		}
		public void info(boolean o) {
			System.out.println(Boolean.toString(o));
		}
		public void info(boolean[] os) {
			System.out.println(Arrays.toString(os));
		}
		public void info(float o) {
			System.out.println(Float.toString(o));
		}
		public void info(float[] os) {
			System.out.println(Arrays.toString(os));
		}
		public void info(double o) {
			System.out.println(Double.toString(o));
		}
		public void info(double[] os) {
			System.out.println(Arrays.toString(os));
		}
		public <T extends Object> void info(T o) {
			System.out.println(o.toString());
		}
		public <T extends Object> void info(T[] os) {
			System.out.println(Arrays.toString(os));
		}
	}
	private SystemManager() {}
}
