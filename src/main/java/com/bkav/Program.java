package com.bkav;

import com.bkav.util.StringUtil;

public class Program {
	public static void main(String[] args) {
		for (String s : StringUtil.splitString("dieu hoa")) {
			System.out.println(s);
		}
	}
}
