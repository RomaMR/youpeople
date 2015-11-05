package com.optigra.youpeople.services.facebook.stat;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.optigra.youpeople.domain.facebook.insights.InsightMetric;

public class DefaultFacebookInsightServiceTest {
	
	private DefaultFacebookInsightService unit = new DefaultFacebookInsightService();
	
	@Test
	@Ignore
	public void testGetMetric() throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String authentificationToken = "CAACEdEose0cBAHi9iBAYenODsYvuZAZBfsawTEezXI2QU6oxL1ZCsHHFuuQ6ZBPCVDJT4QKoUq2H2oAmWnZA5Lpk7IgVCwuRL1Rn3GThMwl0ctxJTwNs8yg3bNieC1jizMJoTTRxLToclkwZCH2PWw293yFZAlezXirTqGziUc3bscri3zZAQ1f16r8H7v03fMnUMRHAoQc27ZBEcMpwfCBsV";
		InsightMetric<Date, Integer> actual = unit.getIntegerTimeSeries("OptigraSoftwareTeam", "page_impressions", sdf.parse("2015-09-10"), sdf.parse("2015-09-23"), null, authentificationToken);
		InsightMetric<Date, Integer> expected = new InsightMetric<>();
		assertEquals(expected.toString(), actual.toString());
	}

}
