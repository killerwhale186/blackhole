package org.galaxy.math;

public enum MathOperator {
	PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");
	
	private final String name;       

    private MathOperator(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
