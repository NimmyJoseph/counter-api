package au.com.optus.counter.api.model;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchResponse {

	private ArrayList<HashMap<String, Integer>> count;

	public ArrayList<HashMap<String, Integer>> getCount() {
		return count;
	}

	public void setCount(ArrayList<HashMap<String, Integer>> count) {
		this.count = count;
	}

}
