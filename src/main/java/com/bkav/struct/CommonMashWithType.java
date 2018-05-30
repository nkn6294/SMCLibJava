package com.bkav.struct;

/***
 * {@link CommonMashWithType} add marks value for {@link CommonMash}
 *
 * @param <T>
 */
public abstract class CommonMashWithType<T> extends CommonMash {
	
	public CommonMashWithType(int length) {
		super(length);
	}
	public CommonMashWithType(int length, byte mode) {
		super(length, mode);
	}
	
	@Override
	protected final void createMarkArray(int length) {
		this.marks = this.createMarkMarkArrayValue(length);
	}
	
	protected T getMarkValueAt(int index) {
		if (!this.checkValidIndex(index)) {
			return null;
		}
		return this.marks[index];
	}
	
	protected boolean setMarkValueAt(int index, T value) {
		if (this.checkValidIndex(index)) {
			this.marks[index] = value;
			return true;
		}
		return false;
	}
	/***
	 * Create marks mark value with <i>length</i> and type
	 * @param length length of array.
	 * @return new marks array with type <b>T</b>
	 */
	protected abstract T[] createMarkMarkArrayValue(int length);
	
	/***
	 * Value save marks
	 */
	protected T[] marks;
}
