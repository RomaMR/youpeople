package com.optigra.youpeople.facades.statistic.facebook;

import com.optigra.youpeople.domain.facebook.insights.InsightAggregationPeriod;
import com.optigra.youpeople.dto.stat.DataSetDTO;

public interface FacebookPageMetricsFacade {
	
	DataSetDTO<Long, Integer> getTimeSeriesMetric(String pageName, String metricName, 
			String facebookAccessToken, Long since, Long until, InsightAggregationPeriod period);
	
}
