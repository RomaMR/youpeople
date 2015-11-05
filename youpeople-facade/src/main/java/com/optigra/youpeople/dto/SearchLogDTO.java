package com.optigra.youpeople.dto;

import java.util.Date;

/**
 * Created by oleh on 06.08.15.
 */
public class SearchLogDTO {

    private Long id;
    private String query;
    private Date searchTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Date getSearchTimestamp() {
        return searchTimestamp;
    }

    public void setSearchTimestamp(Date searchTimestamp) {
        this.searchTimestamp = searchTimestamp;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "id=" + id +
                ", query='" + query + '\'' +
                ", searchTimestamp=" + searchTimestamp +
                '}';
    }
}
