package constants.interfaces;

import java.util.HashMap;

public interface Mapping {

	public static <E extends Enum<E>, V> HashMap<E, V> generateHashMap(V[] ds, E[] values) {
		HashMap<E, V> temp = new HashMap<E, V>();
		
		int index = 0;
		
		for (V factor : ds) {
			temp.put(values[index++], factor);
		}
		
		return temp;
	}
}
