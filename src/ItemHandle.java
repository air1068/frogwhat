package frogwhat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ItemHandle {
	public static String getQualities(String item) {
		return item.substring(item.indexOf('{')+1, item.indexOf('}'));
	}
	
	public static ArrayList<String> splitQualities(String qlist) {
		return new ArrayList<String>(Arrays.asList(qlist.split(",")));
	}
	
	public static <K,V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {
			List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());
			Collections.sort(sortedEntries, new Comparator<Entry<K,V>>() {
				@Override
				public int compare(Entry<K,V> e1, Entry<K,V> e2) {
					return e2.getValue().compareTo(e1.getValue());
				}
			});
			return sortedEntries;
	}
}