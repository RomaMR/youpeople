package com.optigra.youpeople.dto.stat;

import java.util.Map;

public class LineChartDTO<KEY, VALUE> {
	
	private Map<KEY, VALUE> lineChartDataMap;

	public Map<KEY, VALUE> getLineChartDataMap() {
		return lineChartDataMap;
	}

	public void setLineChartDataMap(Map<KEY, VALUE> lineChartDataMap) {
		this.lineChartDataMap = lineChartDataMap;
	}

	@Override
	public String toString() {
		return "LineChartDTO [lineChartDataMap=" + lineChartDataMap + "]";
	}

	

}
