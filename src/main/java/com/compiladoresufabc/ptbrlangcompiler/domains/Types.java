package com.compiladoresufabc.ptbrlangcompiler.domains;

public enum Types {

	NUMBER(1), REALNUMBER(2), TEXT(3), BOOL(4);

	private int value;

	private Types(int valueNumber) {
		this.value = valueNumber;
	}

	public Integer getValue() {
		return this.value;
	}
}
