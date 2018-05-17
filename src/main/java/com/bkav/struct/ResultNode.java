package com.bkav.struct;

import java.io.StringWriter;

public class ResultNode<T> {
	private ResultFind<T> value;
	private ResultNode<?> parent;
	private ResultNode<?> child;

	public ResultNode(ResultNode<?> parent, ResultFind<T> value) {
		this.value = value;
		this.parent = parent;
		this.child = null;
	}

	public void setChild(ResultNode<?> child) {
		this.child = child;
	}

	public ResultFind<T> getValue() {
		return value;
	}

	public ResultNode<?> getParent() {
		return parent;
	}
	
	public ResultNode<?> getChild() {
		return this.child;
	}

	@Override
	public String toString() {
		if (this.child == null) {
			return ":[" + this.value + "]";
		}
		StringWriter writer = new StringWriter();
		writer.append(":[" + this.value + "]");
		writer.append("{");
		writer.append(this.child.toString());
		writer.append("}");
		return writer.toString();
	}

}
