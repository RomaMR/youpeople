package com.optigra.youpeople.domain.audit;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by romanmudryi on 16.08.15.
 */
public class AuditingId implements Serializable{

    @Id
    protected Integer rev;

    @Id
    private Long id;

    public AuditingId() {
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditingId)) return false;

        AuditingId that = (AuditingId) o;

        if (rev != null ? !rev.equals(that.rev) : that.rev != null) return false;
        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        int result = rev != null ? rev.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
