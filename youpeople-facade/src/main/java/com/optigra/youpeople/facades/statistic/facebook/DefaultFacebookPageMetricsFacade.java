package com.optigra.youpeople.facades.statistic.facebook;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.facebook.insights.InsightAggregationPeriod;
import com.optigra.youpeople.domain.facebook.insights.InsightMetric;
import com.optigra.youpeople.dto.stat.DataSetDTO;
import com.optigra.youpeople.services.facebook.stat.FacebookInsightService;

@Component("defaultFacebookPageMetricsFacade")
public class DefaultFacebookPageMetricsFacade implements FacebookPageMetricsFacade {
	
	@Resource(name="defaultFacebookInsightService")
	private FacebookInsightService facebookInsightService;
	
	@Resource(name="facebookLineChartMetricConverter")
	private Converter<InsightMetric<Date, Integer>, DataSetDTO<Long, Integer>> facebookLineChartMetricConverter;

	@Override
	public DataSetDTO<Long, Integer> getTimeSeriesMetric(String pageName, String metricName, 
			String facebookAccessToken, Long since, Long until, InsightAggregationPeriod period) {
		Date sinceDate = since == null ? null : new Date(since);
		Date untilDate = until == null ? null : new Date(until);
		InsightMetric<Date, Integer> insightMetric = 
				facebookInsightService.getIntegerTimeSeries(pageName, metricName, sinceDate, untilDate, period, facebookAccessToken);
		return facebookLineChartMetricConverter.convert(insightMetric);
	}


}
