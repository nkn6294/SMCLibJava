package com.bkav.command.data;

public class CommandModel {
	
    public String command() {
		return command;
	}
	public void command(String command) {
		this.command = command;
	}
	public String[] commands() {
		return commands;
	}
	public void commands(String[] commands) {
		this.commands = commands;
	}
	private String command;
    private String[] commands;
}
