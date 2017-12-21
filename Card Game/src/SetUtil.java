import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUtil {
	
	public static void main(String[] args) {
		Set<Set<Integer>> subs = getSubsetIndex(7, 2);
		System.out.println(subs);
		System.out.println(subs.size());
	}
	
	public static Set<Set<Integer>> getSubsetIndex(int total, int sublen) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < total; i++) {
			list.add(i);
		}
		return getSubsets(list, sublen);
	}
	
	//return all subset with m elements, m must <= list.size
	public static Set<Set<Integer>> getSubsets(List<Integer> list, int m) {
		if (m == 0) {
			Set<Integer> emptySet = new HashSet<Integer>();
			Set<Set<Integer>> ret = new HashSet<Set<Integer>>();
			ret.add(emptySet);
			return ret;
		}
		
		if (list.size() == m) {
			Set<Set<Integer>> cloneSS = new HashSet<Set<Integer>>();
			Set<Integer> clone = new HashSet<Integer>();
			clone.addAll(list);
			cloneSS.add(clone);
			return cloneSS;
		}
		
		Set<Set<Integer>> ret = new HashSet<Set<Integer>>();
		
		List<Integer> excludeFirst = list.subList(1, list.size());

		Set<Set<Integer>> notIncludeFirst = getSubsets(excludeFirst, m);
		ret.addAll(notIncludeFirst);
		
		if (m - 1 > 0) {
			Set<Set<Integer>> ss = getSubsets(excludeFirst, m-1);
			for (Set<Integer> itr : ss) {
				Set<Integer> clone = new HashSet<Integer>();
				clone.addAll(itr);
				clone.add(list.get(0));
				ret.add(clone);
			}
		} else {
			Set<Integer> temp = new HashSet<Integer>();
			temp.add(list.get(0));
			ret.add(temp);
		}

		return ret;
	}
}
