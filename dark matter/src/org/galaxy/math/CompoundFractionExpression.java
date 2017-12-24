package org.galaxy.math;

public class CompoundFractionExpression extends FractionExpression {

	private FractionExpression leftOp;
	private FractionExpression rightOp;
	private MathOperator operator;

	public CompoundFractionExpression(FractionExpression left, FractionExpression right, MathOperator op) {
		this.leftOp = left;
		this.rightOp = right;
		this.operator = op;
	}

	@Override
	public Fraction getValue() {
		if (this.operator == MathOperator.PLUS) {
			return this.leftOp.getValue().add(this.rightOp.getValue());
		} else if (this.operator == MathOperator.MINUS) {
			return this.leftOp.getValue().subtract(this.rightOp.getValue());
		} else if (this.operator == MathOperator.MULTIPLY) {
			return this.leftOp.getValue().multiply(this.rightOp.getValue());
		} else if (this.operator == MathOperator.DIVIDE) {
			return this.leftOp.getValue().divide(this.rightOp.getValue());
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "(" + this.leftOp.toString() + " " + this.operator +  " " + this.rightOp.toString() + ")";
	}
}
