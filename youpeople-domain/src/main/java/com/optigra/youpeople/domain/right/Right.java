package com.optigra.youpeople.domain.right;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the right to do some action, e. g. retrieve data from database,
 * delete data, view admin panel etc.
 * Created by romanmudryi on 06.08.15.
 */
@Entity
@Table(name = "t_right")
public class Right {
	
    @NotNull
    @Size(min = 0, max = 50)
    @Id
    @Column(length = 50)
    private String name;

    public Right() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
