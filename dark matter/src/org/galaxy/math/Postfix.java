package org.galaxy.math;

import java.util.List;
import java.util.Stack;

public class Postfix {
	
	//items in the list must be either Fraction or MathOperator
	//return the result
	public static Fraction evaluate(List<Object> expression) {
		Stack<Object> stack = new Stack<Object>();
		for (Object o : expression) {
			if (o instanceof Fraction) {
				stack.push(o);
			} else {
				MathOperator operator = (MathOperator)o;
				Fraction right = (Fraction) stack.pop();
				Fraction left = (Fraction) stack.pop();
				Fraction res = new Fraction(0);
				switch (operator) {
				case PLUS:
					res = left.add(right);
					break;
				case MINUS:
					res = left.subtract(right);
					break;
				case MULTIPLY:
					res = left.multiply(right);
					break;
				case DIVIDE:
					res = left.divide(right);
					break;
				}
				stack.push(res);
			}
		}
		return (Fraction) stack.pop();
	}
}
