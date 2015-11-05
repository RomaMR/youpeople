package com.optigra.youpeople.services.facebook.stat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.optigra.youpeople.domain.facebook.insights.InsightAggregationPeriod;
import com.optigra.youpeople.domain.facebook.insights.InsightMetric;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.Insight;

@Service("defaultFacebookInsightService")
public class DefaultFacebookInsightService implements FacebookInsightService {
	
	private static final String[] SUPPORTED_METRIC_NAMES = {
			"page_fans", "page_impressions", "page_fan_adds", "page_impressions_organic_unique",
			"page_posts_impressions_unique", "page_consumptions"
	};
	
	private static final String FACEBOOK_LONG_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
	
	private static final DateFormat FACEBOOK_LONG_DATE_FORMAT = new SimpleDateFormat(FACEBOOK_LONG_DATE_FORMAT_PATTERN);

	private Map<Date, Integer> toIntegerTimeSeriesMap(List<JsonObject> dataPoints) throws ParseException {
		Map<Date, Integer> result = new HashMap<>();
		for(JsonObject dataPoint : dataPoints){
			String endDateString = dataPoint.getString("end_time");
			Integer likesCount = dataPoint.getInt("value");
			result.put(FACEBOOK_LONG_DATE_FORMAT.parse(endDateString), likesCount);
		}
		return result;
	}
	
	@Override
	public InsightMetric<Date, Integer> getIntegerTimeSeries(String pageName, String metricName, Date since, Date until, 
			InsightAggregationPeriod period, String authentificationToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(authentificationToken, Version.VERSION_2_4);
		
		List<Parameter> parameterList = new ArrayList<>();
		if(since != null){
			parameterList.add(Parameter.with("since", since));
		}
		if(until != null){
			parameterList.add(Parameter.with("until", until));
		}
		if(period != null){
			parameterList.add(Parameter.with("period", period.getCode()));
		}
		
		Connection<Insight> insights = facebookClient.fetchConnection(pageName + "/insights", Insight.class,
				parameterList.toArray(new Parameter[0]));
		
		Insight selectedMetric = null;
		
		List<Insight> insightList = insights.getData();
		for (Insight insight : insightList){
			  if(insight.getName().equals(metricName)){
				  selectedMetric = insight;
			  }
		}
		if(selectedMetric == null){
			String msg = "The requested metric %s for page %s was not present among %d metrics fetched with Facebook Insights API";
			throw new FacebookInsightException(String.format(msg, metricName, pageName, insightList.size()));
		}
		InsightMetric<Date, Integer> result = new InsightMetric<>();
		result.setId(selectedMetric.getId());
		result.setName(selectedMetric.getName());
		result.setDescription(selectedMetric.getDescription());
		result.setPeriod(selectedMetric.getPeriod());
		try {
			result.setValues(toIntegerTimeSeriesMap(selectedMetric.getValues()));
		} catch (ParseException e) {
			throw new FacebookInsightException(e);
		}
		
		return result;
	}

}
