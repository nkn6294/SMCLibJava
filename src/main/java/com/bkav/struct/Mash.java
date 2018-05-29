package com.bkav.struct;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public interface Mash {
	/***
	 * Normal mode ignore invalid index.
	 */
	public final static byte NORMAL_MODE = 1 << 0;
	/***
	 * Strict mode throw Except when passing invalid index.
	 */
	public final static byte STRICT_MODE = 1 << 1;
	
	public static int MAX_INDEX = 100;
	
	public int length();
	public int maxIndex();
	public int minIndex();
	
	public boolean isMark(int index);
	public boolean isUnMark(int index);
	public boolean[] markValue();
	
	public void setMark(int... indexs);
	public void setMark(Collection<Integer> indexs);
	
	public void setMarkWithRelativeIndex(int... relativeIndexs);
	public void unMarkWithRelativeIndex(int... relativeIndexs);
	
	public int[] markIndexs();
	public IntStream markIndexStream();
	public Iterator<Integer> markInterator();
	
	public int[] unMarkIndexs();
	public IntStream unMarkIndexStream();
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
	public int[][] getFragmentsContainIndexOptimal(int... containIndexs);	
	public int[][] getFragmentsContainIndexOptimal(Collection<Integer> containIndexs);
	
	public void resetFragment(int containIndex);
	public void resetFragments(int... containIndexs);
	public void resetFragments(Collection<Integer> containIndexs);
	public void resetFragmentsOptimal(int... containIndexs);
	public void resetFragmentsOptimal(Collection<Integer> containIndexs);
	
	public int getFragmentEndIndexStartAt(int startIndexInclusive);
	public int[] getFragmentIndexStartAt(int startIndexInclusive);
	public int getFragmenStartIndextEndAt(int endIndexInclusive);
	public int[] getFragmenIndextEndAt(int endIndexInclusive);
}
