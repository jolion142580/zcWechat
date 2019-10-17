package com.gdyiko.zcwx;

public class test {

	public static void main(String[] args) {
		int codeLength = 6;
		String code = "";
		for (int i = 0; i < codeLength; i++) {
			code += (int) (Math.random() * 9);
		}
		System.out.println(code);
	}

}
