package com.optigra.youpeople.services.facebook.stat;

import java.util.Date;

import com.optigra.youpeople.domain.facebook.insights.InsightAggregationPeriod;
import com.optigra.youpeople.domain.facebook.insights.InsightMetric;

public interface FacebookInsightService {
	
	public InsightMetric<Date, Integer> getIntegerTimeSeries(String pageName, String metricName, 
			Date since, Date until, InsightAggregationPeriod period, String authentificationToken);

}
