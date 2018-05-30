package com.bkav.struct;

import java.util.Arrays;

/***
 * {@link CommonMaskWithType} add marks value for {@link CommonMask}
 *
 * @param <T>
 */
public abstract class CommonMaskWithType<T> extends CommonMask {
	
	public CommonMaskWithType(int length) {
		super(length);
	}
	public CommonMaskWithType(int length, MaskConfig config) {
		super(length, config);
	}
	
	@Override
	public String toString() {
		return String.format("%s [marks=%s, minIndex=%s, maxIndex=%s, config=%s]", this.getClass().getSimpleName(),
				Arrays.toString(this.masks), this.minIndex, this.maxIndex, this.config);
	}
	@Override
	public Object[] maskValues() {
		return this.masks;
	}
	@Override
	public Object maskValueAt(int index) {
		return this.getMarkValueAt(index);
	}
	public T[] maskWithTypeValues() {
		return this.masks;
	}
	
	@Override
	protected final void createMarkArray(int length) {
		this.masks = this.createMaskArrayValue(length);
	}
	protected T getMarkValueAt(int index) {
		if (!this.checkValidIndex(index)) {
			return null;
		}
		return this.masks[index];
	}
	
	protected boolean setMarkValueAt(int index, T value) {
		if (this.checkValidIndex(index)) {
			this.masks[index] = value;
			return true;
		}
		return false;
	}
	
	/***
	 * Create marks mark value with <i>length</i> and type
	 * @param length length of array.
	 * @return new marks array with type <b>T</b>
	 */
	protected abstract T[] createMaskArrayValue(int length);
	
	/***
	 * Value save marks
	 */
	protected T[] masks;
}
