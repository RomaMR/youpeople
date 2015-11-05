package com.optigra.youpeople.converter.statistic.facebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.facebook.insights.InsightMetric;
import com.optigra.youpeople.dto.stat.DataSetDTO;


@Component("facebookLineChartMetricConverter")
public class FacebookLineChartMetricConverter extends AbstractConverter<InsightMetric<Date, Integer>, DataSetDTO<Long, Integer>> {

	@Override
	public DataSetDTO<Long, Integer> convert(InsightMetric<Date, Integer> source,
			DataSetDTO<Long, Integer> target) {
		SortedMap<Date, Integer> sourceMap = new TreeMap<>(source.getValues());
		List<DataSetDTO.DataPoint<Long, Integer>> dataPointList = new ArrayList<>();
		for(Map.Entry<Date, Integer> entry : sourceMap.entrySet()){
			DataSetDTO.DataPoint<Long, Integer> datapoint = new DataSetDTO.DataPoint<>();
			datapoint.setKey(entry.getKey().getTime());
			datapoint.setValue(entry.getValue());
			dataPointList.add(datapoint);
		}
		target.setDataPoints(dataPointList);
		return target;
	}

	@Override
	public DataSetDTO<Long, Integer> convert(InsightMetric<Date, Integer> source) {
		DataSetDTO<Long, Integer> target = new DataSetDTO<>();
		return convert(source, target);
	}
 
}
