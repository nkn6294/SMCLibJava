package com.bkav.struct;

/***
 * Fragment: <b><i>Continuity</i></b> words marked in string arrays with
 * <i>startIndex</i>, <i>endIndex</i> and origin data <i>datas</i>
 */
public class Fragment {
	public int startIndex;
	public int endIndex;
	public String[] datas;

	public Fragment(String[] datas, int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.datas = datas;
	}
}