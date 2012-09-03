package at.andiwand.commons.util.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class StringPrefixMap<V> extends HashMap<String, V> {
	
	private static final Comparator<String> lengthComparator = new Comparator<String>() {
		public int compare(String o1, String o2) {
			return o1.length() - o2.length();
		}
	};
	
	private static final long serialVersionUID = 3568257513796247042L;
	
	private List<String> perxfixList = new ArrayList<String>();
	
	@Override
	public V put(String prefix, V value) {
		perxfixList.add(prefix);
		Collections.sort(perxfixList, lengthComparator);
		return super.put(prefix, value);
	}
	
	public V match(String string) {
		for (String prefix : perxfixList) {
			if (string.startsWith(prefix)) return get(prefix);
		}
		
		return null;
	}
	
}