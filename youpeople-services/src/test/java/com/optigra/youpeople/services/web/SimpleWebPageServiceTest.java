package com.optigra.youpeople.services.web;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class SimpleWebPageServiceTest {
	
	private SimpleWebPageService unit = new SimpleWebPageService();
	
	@Test
//	@Ignore
	public void testGet() throws Exception {
		// Given
		String link = "http://example.com?a=b";

		// When
		unit.setProxyEnabled(false);
		String actualResult = unit.get(link);

		// Then
		String expectedResult = "<!doctype html><html><head>    <title>Example Domain</title>";
		assertEquals(expectedResult, actualResult.replaceAll("\n", "").substring(0, 60));

	}

}
