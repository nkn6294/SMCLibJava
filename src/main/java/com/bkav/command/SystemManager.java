package com.bkav.command;

import com.bkav.command.common.TextProcesser;

public class SystemManager {
	public static Logger logger = new Logger();
	public static java.util.logging.Logger logger2 = java.util.logging.Logger.getLogger(System.class.getSimpleName());
	public static TextProcesser textProcesser = new TextProcesser() {
	};
	
	public static class Logger {
		public void info(String o) {
			System.out.println(o.toString());
		}
	}
}
