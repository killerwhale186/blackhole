package org.galaxy.math;

import java.util.Comparator;

public class FractionComparator implements Comparator<FractionExpression> {

	public int compare(FractionExpression f1, FractionExpression f2) {
		Fraction f = f1.getValue().subtract(f2.getValue());
		return f.getSign();
	}

}
