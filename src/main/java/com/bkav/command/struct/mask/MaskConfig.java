package com.bkav.command.struct.mask;

public class MaskConfig {
	/***
	 * Normal mode ignore invalid index.
	 */
	public static final byte NORMAL_MODE = 1 << 0;
	/***
	 * Strict mode throw Except when passing invalid index.
	 */
	public static final byte STRICT_MODE = 1 << 1;
	
	public static final int MAX_INDEX = 100;
	
	public static MaskConfig getDefaultConfig() {
		return defaultConfig;
	}
	
	public MaskConfig(byte config) {
		this.config = config;
	}
	
	public boolean isStrictMode() {
		return (this.config & STRICT_MODE) > 0;
	}
	
	@Override
	public String toString() {
		return String.format("%s [config=%s]", this.getClass().getSimpleName(), this.config);
	}

	protected byte config;
	
	private static MaskConfig defaultConfig = new MaskConfig(NORMAL_MODE);
}
