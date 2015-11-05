package com.optigra.youpeople.services.google;

import java.util.List;

public interface GoogleSearchService {
	
	public List<String> getSearchResultLinks(String query, int offset, int limit);

}
