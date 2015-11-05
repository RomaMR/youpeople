package com.optigra.youpeople.domain.facebook.insights;

/**
 * A token that represents the period over which a Facebook insight metric can be aggregated.
 * Warning: not all metrics support all period values.
 * @see https://developers.facebook.com/docs/graph-api/reference/v2.4/insights
 * @author odisseus
 *
 */
public enum InsightAggregationPeriod {
	
	DAY("day"),
	WEEK("week"),
	DAYS_28("days_28"),
	MONTH("month"),
	YEAR("year"),
	LIFETIME("lifetime");
	
	/**
	 * The code to be supplied to Facebook API.
	 * This is NOT the code expected by our controller.
	 */
	private final String code;
	
	private InsightAggregationPeriod(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static InsightAggregationPeriod fromCode(String code){
		for(InsightAggregationPeriod val : InsightAggregationPeriod.values()){
			if(val.code.equals(val)){
				return val;
			}
		}
		throw new IllegalArgumentException(String.format("Unrecognized InsightAggregationPeriod code: %s", code));
	};

}
