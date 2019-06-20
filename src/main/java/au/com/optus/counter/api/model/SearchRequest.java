package au.com.optus.counter.api.model;

import java.util.ArrayList;

public class SearchRequest {

	private ArrayList<String> searchText;

	public ArrayList<String> getSearchText() {
		return searchText;
	}

	public void setSearchText(ArrayList<String> searchText) {
		this.searchText = searchText;
	}

}
