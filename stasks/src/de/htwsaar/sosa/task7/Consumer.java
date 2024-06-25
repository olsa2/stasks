package de.htwsaar.sosa.task7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Consumer {
	private HashMap<Integer, LinkedList<Long>> checksums = new HashMap<>();

	public void consume(int n) {
		int checksum = 0;
		while (n != 0) {
			checksum += n % 10;
			n = n / 10;
		}

		long timestamp = System.currentTimeMillis();

		LinkedList<Long> timestamps = checksums.get(checksum);
		if (timestamps == null) {
			checksums.put(checksum, timestamps = new LinkedList<Long>());
		}
		timestamps.add(timestamp);
	}
	
	public int numberOfDifferentResults() {
		return checksums.entrySet().size();
	}
	
	public int numberOfOccurrences(int checksum) {
		LinkedList<Long> timestamps = checksums.get(checksum);
		return timestamps != null ? timestamps.size() : 0;
	}
	
	public List<Integer> getCrossTotalsAscending() {
		List<Integer> keys = new ArrayList<>(checksums.keySet());
		Collections.sort(keys);
		return keys;
	}
	
	public List<Integer> getCrossTotalsDescending() {
		List<Integer> keys = new ArrayList<>(checksums.keySet());
		Collections.sort(keys,(x,y)-> -1 * Integer.compare(x, y));
		return keys;
	}
	
	public List<Long> getTimestampsForResult(int checksum) {
		return checksums.get(checksum);
	}
	
}
