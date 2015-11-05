package com.optigra.youpeople.dto.stat;

import java.util.ArrayList;
import java.util.List;

public class DataSetDTO<KEY, VALUE> {
	
	public static class DataPoint<K, V>{
		private K key;
		private V value;
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		
	}
	
	private List<DataPoint<KEY, VALUE>> dataPoints = new ArrayList<>();

	public List<DataPoint<KEY, VALUE>> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<DataPoint<KEY, VALUE>> dataPoints) {
		this.dataPoints = dataPoints;
	}

}
