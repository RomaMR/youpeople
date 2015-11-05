package com.optigra.youpeople.web.controller.stat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.optigra.youpeople.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
		Application.class
})
@IntegrationTest
@WebAppConfiguration
public class FacebookPageMetricControllerTest {
	
	/**
	 * This token becomes stale with time.
	 * Replace it with a new one before running the tests.
	 */
	public static final String facebookAuthToken = "CAANROAL43TwBAFLJ5pjvaAIY4oppAsg1KyfdDNHROlkNU4ppPuwEP3HsH8l5UePaMzydhwoXEBmdpC7g28SKcjGZCbmUYeilAu7w7DZC9qnoBHisOVntEs0SA3kmgvQ65FYpF7JGntBcFX55NgsmRV5C17mqxdL0jX1UfeNTQhvdqtN5fZC33xFNI3wyohhoZAE6FZBsZBBgZDZD";
	
	@Autowired
	public WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.alwaysDo(print())
				.build();
	}
	
	@Test
	@Ignore
	public void test() throws Exception {
		// Given
		
		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/api/facebook/pagemetrics/{pageName}/{metricName}", 
				"GDGLviv", "page_consumptions")
				.param("facebookAuthenticationToken", facebookAuthToken)
				.param("since", "1442011119000")
				.param("until", "1443070319000")
				.param("period", "DAY")
				)
		.andExpect(status().isOk());

	}


}
