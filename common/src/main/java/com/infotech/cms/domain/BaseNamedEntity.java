package com.infotech.cms.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 * base entity with id,name.
 *
 * @author Mohamamd Reza Alagheband
 *
 */
@MappedSuperclass
public abstract class BaseNamedEntity extends BaseEntity {

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", nullable = false, length = 50)
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
