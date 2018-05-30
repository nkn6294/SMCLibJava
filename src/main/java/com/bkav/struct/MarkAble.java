package com.bkav.struct;

public interface MarkAble {
	public void createMarkArray(int length);	
	public void resetMark(int index);
	public void markByIndex(int index) ;
	
	public boolean isMarkByIndex(int index);
	public boolean isUnmarkByIndex(int index);
}
