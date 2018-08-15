package com.bkav.command;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.bkav.command.common.CommandTextProcesser;
import com.bkav.command.common.CommandTextProcesserAdvance;
import com.bkav.command.common.CommonCommandTextProcesser;
import com.bkav.command.common.CommonTextProcesserAdvance;
import com.bkav.command.common.TextProcesser;
import com.bkav.command.util.DateUtils;
import com.bkav.command.util.NormalInputUtils;
import com.bkav.command.util.NumberUtils;
import com.bkav.command.util.TimeUtils;

public class SMCManager {
	public static Logger logger;// = null;
	public static CommandTextProcesser textProcesser;
	/*
	 	SimpleFormatter
	 	 1. format
		 2. data
		 3. source
		 4. logger
		 5. level
		 6. message
		 7. thrown
	 */
	public static final String loggingProperties = String.format("%s\r\n%s\r\n%s\r\n%s\r\n%s",
			"handlers=java.util.logging.ConsoleHandler", 
			".level=ALL", 
			"java.util.logging.ConsoleHandler.level=ALL", 
			"java.util.logging.ConsoleHandler.formatter=java.util.loggin.SimpleFormatter", 
			"java.util.logging.SimpleFormatter.format=[%1$tF %1$tT] [%4$-7s] %5$s %n");
	static {
		ByteArrayInputStream stream = new ByteArrayInputStream(loggingProperties.getBytes());
		try {
			LogManager.getLogManager().readConfiguration(stream);
			//TODO run all Handler in other thread. ???
		} catch (Exception e) {
		}
		logger = Logger.getLogger(SMCManager.class.getSimpleName());
		CommandTextProcesserAdvance textProcesserAdvance = new CommonTextProcesserAdvance();
		textProcesserAdvance.addTextProcesser(new TextProcesser() {
			@Override
			public String apply(String value) {
				return NormalInputUtils.normalInputSplitChar(value);
			}
		});
		textProcesserAdvance.addTextProcesser(new TextProcesser() {
			@Override
			public String apply(String value) {
				return value.toLowerCase();
			}
		});
		textProcesserAdvance.addTextProcesser(new TextProcesser() {
			@Override
			public String apply(String value) {
				return DateUtils.dateToNormal(value);
			}
		});
		textProcesserAdvance.addTextProcesser(new TextProcesser() {
			@Override
			public String apply(String value) {
				return NumberUtils.textToNumber(value);
			}
		});
		textProcesserAdvance.addTextProcesser(new TextProcesser() {
			@Override
			public String apply(String value) {
				return TimeUtils.timeToNormal(value);
			}
		});
		textProcesserAdvance.addTextProcesser(new TextProcesser() {
			@Override
			public String apply(String value) {
				return DateUtils.dateToNormal(value);
			}
		});
		textProcesserAdvance.addTextProcesser(new TextProcesser() {
			@Override
			public String apply(String value) {
				return NormalInputUtils.textToUnit(value);
			}
		});
//		textProcesserAdvance.addTextProcesser(NormalInputUtils::deAccentConvert);
		SMCManager.textProcesserAdvance = textProcesserAdvance;
		textProcesser = new CommonCommandTextProcesser(SMCManager.textProcesserAdvance);
	}
	public static void addTextProcesser(TextProcesser processer) {
		SMCManager.textProcesserAdvance.addTextProcesser(processer);
	}
	public static void removeTextProcesser(TextProcesser processer) {
		SMCManager.textProcesserAdvance.removeTextProcesser(processer);
	}
	public static void loadLogProperties(String fileName) {//logging.properties
		InputStream stream = null;
		try {
			stream = new FileInputStream(fileName);// SystemManager.class.getClassLoader().getResourceAsStream(fileName);
			LogManager.getLogManager().readConfiguration(stream);
		} catch (IOException ex) { 
			System.err.println(ex.getMessage());
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
		}
		logger = Logger.getLogger(System.class.getSimpleName());//new SimpleLogger()
	}
	
	
	public static class SimpleLogger {
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
	private SMCManager() {}
	
	private static CommandTextProcesserAdvance textProcesserAdvance;
}
