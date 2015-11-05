package com.optigra.youpeople.domain.organization;

import com.optigra.youpeople.domain.audit.AbstractEnversAuditing;
import com.optigra.youpeople.domain.audit.AuditingId;

import javax.persistence.Column;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by romanmudryi on 18.08.15.
 */
@IdClass(AuditingId.class)
@Table(name = "t_organization_aud")
public class OrganizationAuditing extends AbstractEnversAuditing implements Serializable{

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    public OrganizationAuditing() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationAuditing)) return false;
        if (!super.equals(o)) return false;

        OrganizationAuditing that = (OrganizationAuditing) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(picture != null ? !picture.equals(that.picture) : that.picture != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }
}
