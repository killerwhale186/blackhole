package org.galaxy.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import org.galaxy.util.*;

//calculate 24 with four numbers from 1 to 13
public class TwentyFour {
	
	private static Fraction goal = new Fraction(24);

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			testRandom();
		}
	}

	private static void testRandom() {
		List<Fraction> list = new ArrayList<Fraction>();
		list.add(new Fraction((int)(1 + Math.random() * 13)));
		list.add(new Fraction((int)(1 + Math.random() * 13)));
		list.add(new Fraction((int)(1 + Math.random() * 13)));
		list.add(new Fraction((int)(1 + Math.random() * 13)));
		String s = getResult(list, goal);
		System.out.println(list + " ===>>> " + s);
	}
	
	public static String getResult(List<Fraction> numbers, Fraction goal) {

		if (numbers.size() == 1) {
			if (numbers.get(0).equals(goal)) {
				return goal.toString();
			} else {
				return null;
			}
		}
		
		Set<Set<Integer>> subsets = SetUtil.getSubsetIndex(numbers.size(), 1);
		for (Set<Integer> subset : subsets) {
			List<Fraction> l1 = getSublist(numbers, subset);
			List<Fraction> l2 = getRemainList(numbers, subset);
			Fraction f1 = l1.get(0);
			String s = getResult(l2, goal.subtract(f1));
			if (s != null) {
				return "(" + f1 + "+" + s + ")";
			}
			s = getResult(l2, goal.add(f1));
			if (s != null) {
				return "(" + s + "-" + f1 + ")";
			}
			s = getResult(l2, goal.divide(f1));
			if (s != null) {
				return "(" + f1 + "*" + s + ")";
			}
			if (!goal.equals(Fraction.Zero)) {
				s = getResult(l2, f1.divide(goal));
				if (s != null) {
					return "(" + f1 + "/" + s + ")";
				}
			}
			s = getResult(l2, f1.subtract(goal));
			if (s != null) {
				return "(" + f1 + "-" + s + ")";
			}
			if (!f1.equals(Fraction.Zero)) {
				s = getResult(l2, goal.multiply(f1));
				if (s != null) {
					return "(" + s + "/" + f1 + ")";
				}
			}
		}
		
		if (numbers.size() >= 4) {
			Set<Set<Integer>> subsets2 = SetUtil.getSubsetIndex(numbers.size(), 2);
			for (Set<Integer> subset2 : subsets2) {
				List<Fraction> l1 = getSublist(numbers, subset2);
				List<Fraction> l2 = getRemainList(numbers, subset2);
				Map<String, Fraction> m1 = getAllResults(l1);
				Map<String, Fraction> m2 = getAllResults(l2);
				for (String s1 : m1.keySet()) {
					for (String s2 : m2.keySet()) {
						Fraction f1 = m1.get(s1);
						Fraction f2 = m2.get(s2);
						if (f1.add(f2).equals(goal)) {
							return s1 + "+" + s2;
						}
						if (f1.subtract(f2).equals(goal)) {
							return s1 + "-" + s2;
						}
						if (f1.multiply(f2).equals(goal)) {
							return s1 + "*" + s2;
						}
						if (!f2.equals(Fraction.Zero)) {
							if (f1.divide(f2).equals(goal)) {
								return s1 + "/" + s2;
							}
						}
						if (f2.subtract(f1).equals(goal)) {
							return s2 + "-" + s1;
						}
						if (!f1.equals(Fraction.Zero)) {
							if (f2.divide(f1).equals(goal)) {
								return s2 + "/" + s1;
							}
						}
					}
				}
			}
		}
		
		return null;
	}
	
	//returns all possible combo/results for two numbers
	private static Map<String, Fraction> getAllResults(List<Fraction> fracs) {
		Map<String, Fraction> ret = new HashMap<String, Fraction>();
		Fraction f1 = fracs.get(0);
		Fraction f2 = fracs.get(1);
		ret.put("(" + f1 + "+" + f2 + ")", f1.add(f2));
		ret.put("(" + f1 + "-" + f2 + ")", f1.subtract(f2));
		ret.put("(" + f1 + "*" + f2 + ")", f1.multiply(f2));
		ret.put("(" + f1 + "/" + f2 + ")", f1.divide(f2));
		ret.put("(" + f2 + "-" + f1 + ")", f2.subtract(f1));
		ret.put("(" + f2 + "/" + f1 + ")", f2.divide(f1));
		return ret;
	}
	
	private static List<Fraction> getSublist(List<Fraction> list, Set<Integer> subset) {
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
