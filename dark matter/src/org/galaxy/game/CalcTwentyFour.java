package org.galaxy.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.galaxy.math.CompositeExpression;
import org.galaxy.math.Fraction;
import org.galaxy.math.FractionComparator;
import org.galaxy.math.FractionExpression;
import org.galaxy.math.MathOperator;

public class CalcTwentyFour {

	private static final Fraction TWENTY_FOUR = new Fraction(24);

	public static void main(String[] args) {
		// testSpecial();
		for (int i = 0; i < 10; i++) {
			testRandom(4);
		}
	}

	private static void testSpecial() {
		testOne(5, 6, 1, 4);
		testOne(1, 6, 11, 13);
		testOne(10, 4, 10, 4);
		testOne(1, 7, 13, 13);
		testOne(1, 1, 13, 1);
		testOne(5, 10, 13, 10);
		testOne(6, 11, 11, 6);
		testOne(5, 5, 5, 1);
		testOne(8, 8, 7, 13);
		testOne(1, 3, 4, 6);
		testOne(1, 6, 6, 8);
		testOne(1, 8, 12, 12);
		testOne(2, 2, 11, 11);
		testOne(2, 3, 5, 12);
		testOne(3, 3, 8, 8);
		testOne(4, 4, 7, 7);
		testOne(5, 5, 7, 11);
		testOne(5, 7, 7, 11);
	}

	private static void testRandom(int n) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			list.add((int) (1 + Math.random() * 13));
		}
		String ans = solve(list);
		System.out.println(list + " ===>>> " + ans);
	}

	private static void testOne(int a, int b, int c, int d) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		String ans = solve(list);
		System.out.println(list + " ===>>> " + ans);
	}

	private static String solve(List<Integer> list) {

		// prepare the initial data
		Map<String, List<FractionExpression>> mp = new HashMap<String, List<FractionExpression>>();
		List<FractionExpression> fe = new ArrayList<FractionExpression>();
		for (Integer n : list) {
			fe.add(new Fraction(n));
		}
		mp.put(getKey(fe), fe);

		int s = list.size();
		while (s > 1) {
			List<int[]> subIndex = getSubIndex(s);
			Map<String, List<FractionExpression>> mp2 = new HashMap<String, List<FractionExpression>>();
			
			for (List<FractionExpression> currList : mp.values()) {
				for (int[] index : subIndex) {
					List<FractionExpression> remains = getRemainItems(currList, s, index[0], index[1]);
					List<FractionExpression> combo = getAllCombo(currList, index[0], index[1]);
					for (FractionExpression f : combo) {
						List<FractionExpression> l2 = new ArrayList<FractionExpression>();
						l2.add(f);
						l2.addAll(remains);
						mp2.put(getKey(l2), l2);
					}
				}
			}

			mp = mp2;
			s--;
		}

		// at this point, we have a list of all possible values (FractionExpression)
		for (List<FractionExpression> currList : mp.values()) {
			FractionExpression f = currList.get(0);
			if (f.getValue().equals(TWENTY_FOUR)) {
				return f.toString();
			}
		}

		return null;
	}

	private static List<FractionExpression> getRemainItems(List<FractionExpression> lst, int s, int i, int j) {
		List<FractionExpression> remains = new ArrayList<FractionExpression>();
		for (int k = 0; k < s; k++) {
			if (k != i && k != j) {
				remains.add(lst.get(k));
			}
		}
		return remains;
	}

	private static List<FractionExpression> getAllCombo(List<FractionExpression> lst, int i, int j) {
		FractionExpression f1 = lst.get(i);
		FractionExpression f2 = lst.get(j);

		List<FractionExpression> list = new ArrayList<FractionExpression>();
		list.add(new CompositeExpression(f1, f2, MathOperator.PLUS));
		list.add(new CompositeExpression(f1, f2, MathOperator.MINUS));
		list.add(new CompositeExpression(f2, f1, MathOperator.MINUS));
		list.add(new CompositeExpression(f1, f2, MathOperator.MULTIPLY));
		if (!f2.getValue().equals(Fraction.Zero)) {
			list.add(new CompositeExpression(f1, f2, MathOperator.DIVIDE));
		}
		if (!f1.getValue().equals(Fraction.Zero)) {
			list.add(new CompositeExpression(f2, f1, MathOperator.DIVIDE));
		}
		return list;
	}

	// used to eliminate duplicates
	private static String getKey(List<FractionExpression> ff) {
		Collections.sort(ff, new FractionComparator());
		StringBuffer buf = new StringBuffer();
		for (FractionExpression f : ff) {
			buf.append("{" + f.getValue() + "}");
		}
		return buf.toString();
	}

	private static List<int[]> getSubIndex(int k) {
		List<int[]> ret = new ArrayList<int[]>();
		for (int i = 0; i < k - 1; i++) {
			for (int j = i + 1; j < k; j++) {
				ret.add(new int[]{i, j});
			}
		}
		return ret;
	}
}
