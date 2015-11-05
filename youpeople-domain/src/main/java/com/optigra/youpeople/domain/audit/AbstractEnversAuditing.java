package com.optigra.youpeople.domain.audit;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by romanmudryi on 16.08.15.
 */
@MappedSuperclass
public class AbstractEnversAuditing extends AbstractAuditingEntity{

    @Id
    private Long id;

    @Column(name = "rev")
    @Id
    protected Integer rev;

    @Column(name = "revtype")
    protected Short revType;

    public AbstractEnversAuditing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public Short getRevType() {
        return revType;
    }

    public void setRevType(Short revType) {
        this.revType = revType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEnversAuditing)) return false;

        AbstractEnversAuditing that = (AbstractEnversAuditing) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rev != null ? !rev.equals(that.rev) : that.rev != null) return false;
        return !(revType != null ? !revType.equals(that.revType) : that.revType != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rev != null ? rev.hashCode() : 0);
        result = 31 * result + (revType != null ? revType.hashCode() : 0);
        return result;
    }
}
