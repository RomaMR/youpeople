package com.optigra.youpeople.dto;

/**
 * Created by romanmudryi on 09.10.15.
 */
public class UploadedPersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String personalEmail;

    private String personalPhone;

    private String country;

    private String city;

    private String school;

    public UploadedPersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(final String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(final String personalPhone) {
        this.personalPhone = personalPhone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(final String school) {
        this.school = school;
    }
}
