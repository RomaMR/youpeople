package com.optigra.youpeople.dto.stat;

import java.util.Map;

public class LocationChartDTO<KEY, VALUE> {
	
	private Map<KEY, VALUE> locationChartDataMap;

	public Map<KEY, VALUE> getLocationChartDataMap() {
		return locationChartDataMap;
	}

	public void setLocationChartDataMap(Map<KEY, VALUE> locationChartDataMap) {
		this.locationChartDataMap = locationChartDataMap;
	}

	@Override
	public String toString() {
		return "LocationChartDTO [locationChartDataMap=" + locationChartDataMap + "]";
	}
	
}
