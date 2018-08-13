package com.bkav.command.struct;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public abstract class MaskWrapper implements Mask {

	public MaskWrapper(Mask mash) {
		this.mash = mash;
	}
	
	public MaskWrapper(int length, MaskConfig config) {
		this.mash = this.createMask(length, config);
	}
	public MaskWrapper(int length) {
		this.mash = this.createMask(length, MaskConfig.getDefaultConfig());
	}
	@Override
	public int length() {
		return this.mash.length();
	}

	@Override
	public int maxIndex() {
		return this.mash.maxIndex();
	}

	@Override
	public int minIndex() {
		return this.mash.minIndex();
	}

	@Override
	public boolean isMark(int index) {
		return this.mash.isMark(index);
	}

	@Override
	public boolean isUnMark(int index) {
		return this.mash.isUnMark(index);
	}

	@Override
	public boolean[] markValue() {
		return this.mash.markValue();
	}

	@Override
	public void setMark(int... indexs) {
		this.mash.setMark(indexs);
	}

	@Override
	public void setMark(Collection<Integer> indexs) {
		this.mash.setMark(indexs);
	}

	@Override
	public void setMarkWithRelativeIndex(int... relativeIndexs) {
		this.mash.setMarkWithRelativeIndex(relativeIndexs);
	}
	@Override
	public void setMarkWithRelativeIndex(Collection<Integer> relativeIndexs) {
		this.mash.setMarkWithRelativeIndex(relativeIndexs);
	}
	@Override
	public void unMarkWithRelativeIndex(int... relativeIndexs) {
		this.mash.unMarkWithRelativeIndex(relativeIndexs);
	}
	
	@Override
	public void unMarkWithRelativeIndex(Collection<Integer> relativeIndexs) {
		this.mash.unMarkWithRelativeIndex(relativeIndexs);
	}

	@Override
	public int[] markIndexs() {
		return this.mash.markIndexs();
	}

	@Override
	public IntStream markIndexStream() {
		return this.mash.markIndexStream();
	}

	@Override
	public Iterator<Integer> markInterator() {
		return this.mash.markInterator();
	}

	@Override
	public int[] unMarkIndexs() {
		return this.mash.unMarkIndexs();
	}

	@Override
	public IntStream unMarkIndexStream() {
		return this.mash.unMarkIndexStream();
	}

	@Override
	public Iterator<Integer> unMarkInterator() {
		return this.mash.unMarkInterator();
	}

	@Override
	public int[][] getFragmentIndexs() {
		return this.mash.getFragmentIndexs();
	}

	@Override
	public int[][] getUnMarkIndexs() {
		return this.mash.getUnMarkIndexs();
	}

	@Override
	public void resetByIndex(int... indexs) {
		this.mash.resetByIndex(indexs);
	}

	@Override
	public void resetByIndex(Collection<Integer> indexs) {
		this.mash.resetByIndex(indexs);
	}

	@Override
	public void reset() {
		this.mash.reset();
	}

	@Override
	public int[] getFragmentIndex(int containIndex, Consumer<Integer> consumer) {
		return this.mash.getFragmentIndex(containIndex, consumer);
	}

	@Override
	public int[] getFragmentIndex(int containIndex) {
		return this.mash.getFragmentIndex(containIndex);
	}

	@Override
	public int[][] getFragmentsContainIndex(int... containIndexs) {
		return this.mash.getFragmentsContainIndex(containIndexs);
	}

	@Override
	public int[][] getFragmentsContainIndex(Collection<Integer> containIndexs) {
		return this.mash.getFragmentsContainIndex(containIndexs);
	}

	@Override
	public int[][] getFragmentsContainIndexOptimal(int... containIndexs) {
		return this.mash.getFragmentsContainIndexOptimal(containIndexs);
	}

	@Override
	public int[][] getFragmentsContainIndexOptimal(Collection<Integer> containIndexs) {
		return this.mash.getFragmentsContainIndexOptimal(containIndexs);
	}

	@Override
	public void resetFragment(int containIndex) {
		this.mash.resetFragment(containIndex);
	}

	@Override
	public void resetFragments(int... containIndexs) {
		this.mash.resetFragments(containIndexs);
	}

	@Override
	public void resetFragments(Collection<Integer> containIndexs) {
		this.mash.resetFragments(containIndexs);
	}

	@Override
	public void resetFragmentsOptimal(int... containIndexs) {
		this.mash.resetFragmentsOptimal(containIndexs);
	}

	@Override
	public void resetFragmentsOptimal(Collection<Integer> containIndexs) {
		this.mash.resetFragmentsOptimal(containIndexs);	
	}

	@Override
	public int getFragmentEndIndexStartAt(int startIndexInclusive) {
		return this.mash.getFragmentEndIndexStartAt(startIndexInclusive);
	}

	@Override
	public int[] getFragmentIndexStartAt(int startIndexInclusive) {
		return this.mash.getFragmentIndexStartAt(startIndexInclusive);
	}

	@Override
	public int getFragmenStartIndextEndAt(int endIndexInclusive) {
		return this.mash.getFragmenStartIndextEndAt(endIndexInclusive);
	}

	@Override
	public int[] getFragmenIndextEndAt(int endIndexInclusive) {
		return this.mash.getFragmenIndextEndAt(endIndexInclusive);
	}
	
	@Override
	public void setConfig(MaskConfig config) {
		this.mash.setConfig(config);
	}
	
	@Override
	public MaskConfig config() {
		return this.mash.config();
	}
	
	@Override
	public boolean checkValidIndex(int index) {
		return this.mash.checkValidIndex(index);
	}
	
	@Override
	public boolean isValidIndex(int index) {
		return this.mash.isValidIndex(index);
	}
	
	public Mask mash() {
		return this.mash;
	}
	
	@Override
	public String toString() {
		return String.format("%s [mash=%s]", this.getClass().getSimpleName(), mash);
	}

	@Override
	public Object[] maskValues() {
		return this.mash.maskValues();
	}

	@Override
	public Object maskValueAt(int index) {
		return this.mash.maskValueAt(index);
	}
	protected Mask mash;
	
	protected abstract Mask createMask(int length, MaskConfig config);
}
