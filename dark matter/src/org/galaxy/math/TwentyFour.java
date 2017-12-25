package org.galaxy.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.galaxy.util.SetUtil;

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
		testOne(5, 5, 5, 1);
		testOne(8, 8, 7, 13);
		
		for (int i = 0; i < 20; i++) {
			testRandom();
		}
	}
	
	private static void testRandom() {
		List<Integer> list = new ArrayList<Integer>();
		list.add((int)(1 + Math.random() * 13));
		list.add((int)(1 + Math.random() * 13));
		list.add((int)(1 + Math.random() * 13));
		list.add((int)(1 + Math.random() * 13));
		//list.add((int)(1 + Math.random() * 13));
		//list.add((int)(1 + Math.random() * 13));
		FractionExpression exp = findSolution(list, FinalGoal);
		System.out.println(list + " ===>>> " + exp);
	}
	
	private static void testOne(int a, int b, int c, int d) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		FractionExpression exp = findSolution(list, FinalGoal);
		System.out.println(list + " ===>>> " + exp);
	}
	
	public static FractionExpression findSolution(List<Integer> list, Fraction goal) {
		Collections.sort(list);
		List<Fraction> listFraction = new ArrayList<Fraction>();
		for (Integer i : list) {
			listFraction.add(new Fraction(i));
		}
 		Map<String, Map<Fraction, FractionExpression>> mp = new HashMap<String, Map<Fraction, FractionExpression>>();
		for (int level = 1; level <= list.size(); level++) {
			buildLevel(level, mp, listFraction);
		}
		return mp.get(getKey(listFraction)).get(goal);
	}
	
	private static void buildLevel(int level, Map<String, Map<Fraction, FractionExpression>> mp, List<Fraction> list) {
		if (level == 1) {
			for (Fraction f : list) {
				Map<Fraction, FractionExpression> submp = new HashMap<Fraction, FractionExpression>();
				submp.put(f.getValue(), f);
				mp.put("(" + f.getValue() + ")", submp);
			}
			return;
		}
		
		Set<Set<Integer>> subsetsIndex = SetUtil.getSubsetIndex(list.size(), level);
		for (Set<Integer> subsetIndex : subsetsIndex) {
			List<Fraction> sublist = getSubList(list, subsetIndex);
			String key = getKey(sublist);
			Map<Fraction, FractionExpression> submp = new HashMap<Fraction, FractionExpression>();
			mp.put(key, submp);
			for (int k = 1; k <= level / 2; k++) {
				Set<Set<Integer>> subsubsetsIndex = SetUtil.getSubsetIndex(sublist.size(), k);
				for (Set<Integer> subsubsetIndex : subsubsetsIndex) {
					List<Fraction> subsubset1 = getSubList(sublist, subsubsetIndex);
					List<Fraction> subsubset2 = getRemainList(sublist, subsubsetIndex);
					Map<Fraction, FractionExpression> subsubmp1 = mp.get(getKey(subsubset1));
					Map<Fraction, FractionExpression> subsubmp2 = mp.get(getKey(subsubset2));
					for (Fraction f1 : subsubmp1.keySet()) {
						for (Fraction f2 : subsubmp2.keySet()) {
							FractionExpression exp = new CompoundFractionExpression(subsubmp1.get(f1), subsubmp2.get(f2), MathOperator.PLUS);
							Fraction val = f1.add(f2);
							submp.put(val,  exp);
							
							FractionExpression exp2 = new CompoundFractionExpression(subsubmp1.get(f1), subsubmp2.get(f2), MathOperator.MINUS);
							Fraction val2 = f1.subtract(f2);
							submp.put(val2,  exp2);
							
							FractionExpression exp3 = new CompoundFractionExpression(subsubmp1.get(f1), subsubmp2.get(f2), MathOperator.MULTIPLY);
							Fraction val3 = f1.multiply(f2);
							submp.put(val3,  exp3);
							
							if (!f2.getValue().equals(Fraction.Zero)) {
								FractionExpression exp4 = new CompoundFractionExpression(subsubmp1.get(f1), subsubmp2.get(f2), MathOperator.DIVIDE);
								Fraction val4 = f1.divide(f2);
								submp.put(val4,  exp4);
							}
							
							FractionExpression exp5 = new CompoundFractionExpression(subsubmp2.get(f2), subsubmp1.get(f1), MathOperator.MINUS);
							Fraction val5 = f2.subtract(f1);
							submp.put(val5,  exp5);
							
							if (!f1.getValue().equals(Fraction.Zero)) {
								FractionExpression exp6 = new CompoundFractionExpression(subsubmp2.get(f2), subsubmp1.get(f1), MathOperator.DIVIDE);
								Fraction val6 = f2.divide(f1);
								submp.put(val6,  exp6);
							}
						}
					}
				}
			}
		}
	}
	
	private static String getKey(List<Fraction> list) {
		StringBuffer buf = new StringBuffer();
		for (Fraction f : list) {
			buf.append("(").append(f.toString()).append(")");
		}
		return buf.toString();
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