package com.optigra.youpeople.domain.user.organization;

import com.optigra.youpeople.domain.audit.AbstractAuditingEntity;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.user.User;

import javax.persistence.*;

/**
 * Created by romanmudryi on 13.10.15.
 */
@Entity
@Table(name = "t_user_organization")
public class UserOrganization extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "position")
    private String position;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "facebook")
    private String facebook;

    public UserOrganization() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(final String linkedin) {
        this.linkedin = linkedin;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(final String facebook) {
        this.facebook = facebook;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOrganization)) return false;

        final UserOrganization that = (UserOrganization) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (organization != null ? !organization.equals(that.organization) : that.organization != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (linkedin != null ? !linkedin.equals(that.linkedin) : that.linkedin != null) return false;
        return !(facebook != null ? !facebook.equals(that.facebook) : that.facebook != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (linkedin != null ? linkedin.hashCode() : 0);
        result = 31 * result + (facebook != null ? facebook.hashCode() : 0);
        return result;
    }
}
