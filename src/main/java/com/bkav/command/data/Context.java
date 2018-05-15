package com.bkav.command.data;

import java.time.LocalDate;
import java.time.LocalTime;

/***
 * 
 * @author NamNK
 * 
 * @ Information current: time, room, status.
 */
public class Context {
	public long getCurrentTime() {
		return System.currentTimeMillis();
	}
	
	public LocalDate getCurrentDate() {
		return LocalDate.now();
	}
	
	public LocalTime getNow() {
		return LocalTime.now();
	}
	
	public void setCurrentRoom(String currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public String getCurrentRoom() {
		return this.currentRoom;
	}
	
	public String getAMOrPM() {
		LocalTime time = this.getNow();
		return time.getHour() >= 12 ? "PM" : "AM";
	}
	
	private String currentRoom;
}


