package com.optigra.youpeople.dto.stat;

import java.util.Map;

public class BarChartDTO<KEY, VALUE> {
	
	private Map<KEY, VALUE> barChartDataMap;

	public Map<KEY, VALUE> getBarChartDataMap() {
		return barChartDataMap;
	}

	public void setBarChartDataMap(Map<KEY, VALUE> barChartDataMap) {
		this.barChartDataMap = barChartDataMap;
	}

	@Override
	public String toString() {
		return "BarChartDTO [barChartDataMap=" + barChartDataMap + "]";
	}

}
