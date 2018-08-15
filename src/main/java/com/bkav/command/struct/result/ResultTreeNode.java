package com.bkav.command.struct.result;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultTreeNode<T> {
	private ResultFind<T> value;
	private ResultTreeNode<?> parent;
	private List<ResultTreeNode<?>> childs;

	public ResultTreeNode(ResultTreeNode<?> parent, ResultFind<T> value) {
		this.value = value;
		this.parent = parent;
		this.childs = new ArrayList<>();
	}

	public void addChild(ResultTreeNode<?> child) {
		this.childs.add(child);
	}

	public ResultFind<T> getValue() {
		return value;
	}

	public ResultTreeNode<?> getParent() {
		return parent;
	}

	public List<ResultTreeNode<?>> getChilds() {
		return childs;
	}
	
	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		writer.append(":[" + this.value + "]");
		if (this.childs.isEmpty()) {
			return writer.toString();
		}
		writer.append("{");
		Iterator<ResultTreeNode<?>> iterator = this.childs.iterator();
		while (iterator.hasNext()) {
			ResultTreeNode<?> node = iterator.next();
			writer.append("{");
			writer.append(node.toString());
			writer.append("}");
			if (iterator.hasNext()) {
				writer.append(";");
			}
		}
		writer.append("}");
		return writer.toString();
	}

}
