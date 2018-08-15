package com.bkav.command.struct.mask;

/***
 * Fragment: <b><i>Continuity</i></b> words marked in data arrays with
 * <i>startIndex</i>, <i>endIndex</i> and origin data <i>datas</i>
 */
public class Fragment<T, R extends Object> {

	public Fragment(T[] datas, int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.datas = datas;
	}
	
	public R getValue() {
		return getValue();
	}
	
	protected R value;
	protected int startIndex;
	protected int endIndex;
	protected final T[] datas;
	
}