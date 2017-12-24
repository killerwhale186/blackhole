package org.galaxy.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.galaxy.util.*;

//calculate 24 with four numbers from 1 to 13
public class TwentyFour {
	
	private static Fraction FinalGoal = new Fraction(24);

	public static void main(String[] args) {

		testOne(5, 6, 1, 4);
		testOne(1, 6, 11, 13);
		testOne(10, 4, 10, 4);
		testOne(1, 7, 13, 13);
		testOne(1, 1, 13, 1);
		testOne(5, 10, 13, 10);
		testOne(6, 11, 11, 6);
		
		for (int i = 0; i < 20; i++) {
			testRandom();
		}
	}
	
	private static void testOne(int a, int b, int c, int d) {
		List<Fraction> list = new ArrayList<Fraction>();
		list.add(new Fraction(a));
		list.add(new Fraction(b));
		list.add(new Fraction(c));
		list.add(new Fraction(d));
		FractionExpression exp = findSolution(list, FinalGoal);
		System.out.println(list + " ===>>> " + exp);
	}
	
	private static void testRandom() {
		List<Fraction> list = new ArrayList<Fraction>();
		list.add(new Fraction((int)(1 + Math.random() * 13)));
		list.add(new Fraction((int)(1 + Math.random() * 13)));
		list.add(new Fraction((int)(1 + Math.random() * 13)));
		list.add(new Fraction((int)(1 + Math.random() * 13)));
		FractionExpression exp = findSolution(list, FinalGoal);
		System.out.println(list + " ===>>> " + exp);
	}
	
	public static FractionExpression findSolution(List<Fraction> numbers, Fraction goal) {

		if (numbers.size() == 1) {
			if (numbers.get(0).equals(goal)) {
				return numbers.get(0);
			} else {
				return null;
			}
		}
		
		Set<Set<Integer>> subsets = SetUtil.getSubsetIndex(numbers.size(), 1);
		for (Set<Integer> subset : subsets) {
			List<Fraction> l1 = getSubList(numbers, subset);
			List<Fraction> l2 = getRemainList(numbers, subset);
			Fraction f1 = l1.get(0);
			FractionExpression s = getDesired(f1, l2, goal);
			if (s != null) {
				return s;
			}
		}
		
		if (numbers.size() >= 4) {
			Set<Set<Integer>> subsets2 = SetUtil.getSubsetIndex(numbers.size(), 2);
			for (Set<Integer> subset2 : subsets2) {
				List<Fraction> l1 = getSubList(numbers, subset2);
				List<Fraction> l2 = getRemainList(numbers, subset2);
				List<FractionExpression> flist = getAllResults(l1);
				for (FractionExpression f1 : flist) {
					FractionExpression s = getDesired(f1, l2, goal);
					if (s != null) {
						return s;
					}
				}
			}
		}
		
		return null;
	}
	
	private static FractionExpression getDesired(FractionExpression f1, List<Fraction> l2, Fraction goal) {
		FractionExpression s = null;
		//
		s = findSolution(l2, goal.subtract(f1.getValue()));
		if (s != null) {
			return new CompoundFractionExpression(f1, s, MathOperator.PLUS);
		}
		//
		s = findSolution(l2, goal.add(f1.getValue()));
		if (s != null) {
			return new CompoundFractionExpression(s, f1, MathOperator.MINUS);
		}
		//
		if (!f1.getValue().equals(Fraction.Zero)) {
			s = findSolution(l2, goal.divide(f1.getValue()));
			if (s != null) {
				return new CompoundFractionExpression(f1, s, MathOperator.MULTIPLY);
			}
		}
		//
		if (!goal.equals(Fraction.Zero)) {
			s = findSolution(l2, f1.getValue().divide(goal));
			if (s != null && !s.getValue().equals(Fraction.Zero)) {
				return new CompoundFractionExpression(f1, s, MathOperator.DIVIDE);
			}
		}
		//
		s = findSolution(l2, f1.getValue().subtract(goal));
		if (s != null) {
			return new CompoundFractionExpression(f1, s, MathOperator.MINUS);
		}
		//
		if (!f1.getValue().equals(Fraction.Zero) && !goal.equals(Fraction.Zero)) {
			s = findSolution(l2, goal.multiply(f1.getValue()));
			if (s != null) {
				return new CompoundFractionExpression(s, f1, MathOperator.DIVIDE);
			}
		}
		return null;
	}
	
	//returns all possible combo/results for two numbers
	private static List<FractionExpression> getAllResults(List<Fraction> fracs) {
		List<FractionExpression> ret = new ArrayList<FractionExpression>();
		Fraction f1 = fracs.get(0);
		Fraction f2 = fracs.get(1);
		ret.add(new CompoundFractionExpression(f1, f2, MathOperator.PLUS));
		ret.add(new CompoundFractionExpression(f1, f2, MathOperator.MINUS));
		ret.add(new CompoundFractionExpression(f2, f1, MathOperator.MINUS));
		ret.add(new CompoundFractionExpression(f1, f2, MathOperator.MULTIPLY));
		if (!f2.getValue().equals(Fraction.Zero)) {
			ret.add(new CompoundFractionExpression(f1, f2, MathOperator.DIVIDE));
		}
		if (!f1.getValue().equals(Fraction.Zero)) {
			ret.add(new CompoundFractionExpression(f2, f1, MathOperator.DIVIDE));
		}
		return ret;
	}
	
	private static List<Fraction> getSubList(List<Fraction> list, Set<Integer> subset) {
		List<Fraction> ret = new ArrayList<Fraction>();
		for (Integer i : subset) {
			ret.add(list.get(i));
		}
		return ret;
	}
	
	private static List<Fraction> getRemainList(List<Fraction> list, Set<Integer> subset) {
		List<Fraction> ret = new ArrayList<Fraction>();
		for (Integer i = 0; i < list.size(); i++) {
			if (subset.contains(i) == false) {
				ret.add(list.get(i));
			}
		}
		return ret;
	}

}
