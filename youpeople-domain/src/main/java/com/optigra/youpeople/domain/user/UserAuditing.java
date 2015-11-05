package com.optigra.youpeople.domain.user;

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
@Table(name = "t_user")
public class UserAuditing extends AbstractEnversAuditing implements Serializable{

    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @Column(length = 100)
    private String password;

    public UserAuditing() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuditing)) return false;
        if (!super.equals(o)) return false;

        UserAuditing that = (UserAuditing) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return !(password != null ? !password.equals(that.password) : that.password != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
