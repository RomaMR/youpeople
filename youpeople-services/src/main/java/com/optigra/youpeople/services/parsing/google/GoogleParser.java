package com.optigra.youpeople.services.parsing.google;

import java.util.List;

public interface GoogleParser {
	
	public List<String> parseSearchResultLinks (String htmlPage);

}
