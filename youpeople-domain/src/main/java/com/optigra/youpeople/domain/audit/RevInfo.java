package com.optigra.youpeople.domain.audit;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by romanmudryi on 16.08.15.
 */
@Entity
@Table(name = "t_revision_info")
@RevisionEntity(value = DefaultRevisionListener.class)
public class RevInfo {
	
	@Id
    @GeneratedValue
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    private long timestamp;

    @Column(name = "audited_by")
    private String auditedBy;

    public String getAuditedBy() {
        return auditedBy;
    }

    public void setAuditedBy(String auditedBy) {
        this.auditedBy = auditedBy;
    }

    public RevInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RevInfo)) return false;
        if (!super.equals(o)) return false;

        RevInfo revInfo = (RevInfo) o;

        return !(auditedBy != null ? !auditedBy.equals(revInfo.auditedBy) : revInfo.auditedBy != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (auditedBy != null ? auditedBy.hashCode() : 0);
        return result;
    }
}
