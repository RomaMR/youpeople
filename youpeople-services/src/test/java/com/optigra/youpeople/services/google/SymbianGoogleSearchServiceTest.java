package com.optigra.youpeople.services.google;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class SymbianGoogleSearchServiceTest {
	
	private SymbianGoogleSearchService unit = new SymbianGoogleSearchService();
	
	@Test
	@Ignore
	public void testName() throws Exception {
		// Given
		String searchQuery = "DIO-Soft site:linkedin.com";
		int offset = 3;
		int limit = 14;

		// When
		List<String> actualResult = unit.getSearchResultLinks(searchQuery, offset, limit);

		// Then
		List<String> expectedResult = Arrays.asList("https://www.linkedin.com/company/optigra-software",
				"https://www.linkedin.com/in/optigrasoftwarekeepintouch",
				"https://ua.linkedin.com/pub/optigra-career/a3/508/291",
				"https://www.linkedin.com/today/influencer%3FauthorId%3D71814179",
				"https://ua.linkedin.com/pub/myroslav-golub/40/654/932/uk",
				"https://ua.linkedin.com/in/khrystynapelenychka", "https://ua.linkedin.com/in/ozasadnyy",
				"https://www.linkedin.com/directory/topics-companies-o-68-45",
				"https://ua.linkedin.com/pub/taras-voronjak/74/628/105",
				"https://it.linkedin.com/pub/lubomir-bregman/20/73/16b");
		assertEquals(expectedResult.size(), actualResult.size());
		assertEquals(expectedResult.toString(), actualResult.toString());
		assertEquals(expectedResult, actualResult);

	}

}
