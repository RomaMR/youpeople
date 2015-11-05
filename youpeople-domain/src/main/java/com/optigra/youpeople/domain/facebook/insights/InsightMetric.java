package com.optigra.youpeople.domain.facebook.insights;

import java.util.Date;
import java.util.Map;

/**
 * MG -- Currently not intended to be persisted, so no JPA or Hibernate annotations here.
 * @author odisseus
 *
 */
public class InsightMetric<KEY, VALUE> {
	
	protected String id;
	
	protected String name;
	
	protected String description;
	
	protected String period;
	
	protected Map<KEY, VALUE> values;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Map<KEY, VALUE> getValues() {
		return values;
	}

	public void setValues(Map<KEY, VALUE> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "InsightMetric [id=" + id + ", name=" + name + ", description=" + description + ", period=" + period
				+ ", values=" + values + "]";
	}
	
	

}
