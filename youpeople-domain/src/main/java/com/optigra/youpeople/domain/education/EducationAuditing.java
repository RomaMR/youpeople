package com.optigra.youpeople.domain.education;

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
@Table(name = "t_education_aud")
public class EducationAuditing extends AbstractEnversAuditing implements Serializable {

    @Column(name = "degree")
    private String degree;

    @Column(name = "field_of_study")
    private String fieldOfStudy;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public EducationAuditing() {
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EducationAuditing)) return false;
        if (!super.equals(o)) return false;

        EducationAuditing that = (EducationAuditing) o;

        if (degree != null ? !degree.equals(that.degree) : that.degree != null) return false;
        if (fieldOfStudy != null ? !fieldOfStudy.equals(that.fieldOfStudy) : that.fieldOfStudy != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        return !(endDate != null ? !endDate.equals(that.endDate) : that.endDate != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (fieldOfStudy != null ? fieldOfStudy.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
