package com.optigra.youpeople.dto.createperson;

/**
 * Created by romanmudryi on 19.10.15.
 */
public class CreatedPersonEducationDTO {

    private String degree;

    private String fieldOfStudy;

    public CreatedPersonEducationDTO() {
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(final String degree) {
        this.degree = degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(final String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }
}
