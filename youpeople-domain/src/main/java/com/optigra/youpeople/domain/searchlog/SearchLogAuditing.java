package com.optigra.youpeople.domain.searchlog;

import com.optigra.youpeople.domain.audit.AbstractEnversAuditing;
import com.optigra.youpeople.domain.audit.AuditingId;

import javax.persistence.Column;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by romanmudryi on 18.08.15.
 */
@IdClass(AuditingId.class)
@Table(name = "t_search_log_aud")
public class SearchLogAuditing extends AbstractEnversAuditing implements Serializable{

    @Column(name = "query")
    private String query;

    @Column(name = "search_timestamp")
    private Date searchTimestamp;

    public SearchLogAuditing() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchLogAuditing)) return false;

        SearchLogAuditing that = (SearchLogAuditing) o;

        if (query != null ? !query.equals(that.query) : that.query != null) return false;
        return !(searchTimestamp != null ? !searchTimestamp.equals(that.searchTimestamp) : that.searchTimestamp != null);

    }

    @Override
    public int hashCode() {
        int result = query != null ? query.hashCode() : 0;
        result = 31 * result + (searchTimestamp != null ? searchTimestamp.hashCode() : 0);
        return result;
    }
}
