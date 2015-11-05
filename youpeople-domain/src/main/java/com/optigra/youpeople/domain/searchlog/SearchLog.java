package com.optigra.youpeople.domain.searchlog;

import com.optigra.youpeople.domain.audit.AbstractAuditingEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by romanmudryi on 05.08.15.
 */
@Entity
@Table(name = "t_search_log")
@Audited
public class SearchLog extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "query")
    private String query;

    @Column(name = "search_timestamp")
    private Date searchTimestamp;

    public SearchLog() {
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchLog)) return false;

        SearchLog searchLog = (SearchLog) o;

        if (id != null ? !id.equals(searchLog.id) : searchLog.id != null) return false;
        if (query != null ? !query.equals(searchLog.query) : searchLog.query != null) return false;
        return !(searchTimestamp != null ? !searchTimestamp.equals(searchLog.searchTimestamp) : searchLog.searchTimestamp != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (query != null ? query.hashCode() : 0);
        result = 31 * result + (searchTimestamp != null ? searchTimestamp.hashCode() : 0);
        return result;
    }
}
