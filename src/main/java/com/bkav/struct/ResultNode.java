package com.bkav.struct;

import java.io.StringWriter;

public class ResultNode<T> {
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
		StringWriter writer = new StringWriter();
		writer.append(":[" + this.value + "]");
		if (this.child != null) {
			writer.append("{");
			writer.append(this.child.toString());
			writer.append("}");			
		}
		return writer.toString();
	}

	private ResultFind<T> value;
	private ResultNode<?> parent;
	private ResultNode<?> child;
}
