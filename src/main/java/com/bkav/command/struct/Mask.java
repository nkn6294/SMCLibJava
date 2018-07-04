package com.bkav.command.struct;

import java.util.Collection;
import java.util.Iterator;

import com.bkav.command.common.Consumer;

public interface Mask {

	public int length();
	public int maxIndex();
	public int minIndex();
	
	public boolean isMark(int index);
	public boolean isUnMark(int index);
	public boolean[] markValue();
	public Object[] maskValues();
	public Object maskValueAt(int index);
	
	public void setMark(int... indexs);
	public void setMark(Collection<Integer> indexs);
	
	public void setMarkWithRelativeIndex(Collection<Integer> relativeIndexs);
	public void setMarkWithRelativeIndex(int... relativeIndexs);
	public void unMarkWithRelativeIndex(Collection<Integer> relativeIndexs);
	public void unMarkWithRelativeIndex(int... relativeIndexs);
	
	public int[] markIndexs();
	public Iterator<Integer> markInterator();
	
	public int[] unMarkIndexs();
	public Iterator<Integer> unMarkInterator();
	
	public int[][] getFragmentIndexs();
	public int[][] getUnMarkIndexs();
	
	public void resetByIndex(int... indexs);
	public void resetByIndex(Collection<Integer> indexs);
	public void reset();
	
	public int[] getFragmentIndex(int containIndex, Consumer<Integer> consumer);
	public int[] getFragmentIndex(int containIndex);
	public int[][] getFragmentsContainIndex(int... containIndexs);
	public int[][] getFragmentsContainIndex(Collection<Integer> containIndexs);
	
	public void resetFragment(int containIndex);
	public void resetFragments(int... containIndexs);
	public void resetFragments(Collection<Integer> containIndexs);
	
	public int getFragmentEndIndexStartAt(int startIndexInclusive);
	public int[] getFragmentIndexStartAt(int startIndexInclusive);
	public int getFragmenStartIndextEndAt(int endIndexInclusive);
	public int[] getFragmenIndextEndAt(int endIndexInclusive);

	public boolean checkValidIndex(int index);
	public boolean isValidIndex(int index);
	
	public void setConfig(MaskConfig config);
	public MaskConfig config();
	
	public void resetFragmentsOptimal(int... containIndexs);
	public int[][] getFragmentsContainIndexOptimal(int... containIndexs);
	
	public void resetFragmentsOptimal(Collection<Integer> containIndexs);
	public int[][] getFragmentsContainIndexOptimal(Collection<Integer> containIndexs);
}
