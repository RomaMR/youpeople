package com.optigra.youpeople.dto.stat;

import java.util.Map;

public class PieChartDTO<KEY, VALUE> {
	
	private Map<KEY, VALUE> pieChartDataMap;

	public Map<KEY, VALUE> getPieChartDataMap() {
		return pieChartDataMap;
	}

	public void setPieChartDataMap(Map<KEY, VALUE> pieChartDataMap) {
		this.pieChartDataMap = pieChartDataMap;
	}

	@Override
	public String toString() {
		return "PieChartDTO [pieChartDataMap=" + pieChartDataMap + "]";
	}

}
