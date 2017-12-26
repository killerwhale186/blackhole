package org.galaxy.math;

public enum MathOperator {
	PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");
	
	private final String name;       

    private MathOperator(String s) {
        name = s;
    }

    public String toString() {
       return this.name;
    }
}
