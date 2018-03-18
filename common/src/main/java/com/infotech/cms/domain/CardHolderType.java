package com.infotech.cms.domain;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * simple business object representing cardHolder type for cardHolder.
 *
 * @author Mohamamd Reza Alagheband
 *
 */
@Entity
@Table(name="card_holder_type")
public class CardHolderType extends BaseEntity {

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("CardHolderType[id:%d, name:%s]", id, name);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardHolderType)) {
            return false;
        }
        CardHolderType cardHolderType = (CardHolderType)other;
        if (this.id == cardHolderType.id) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = (31 * result) + id.hashCode();
        }
        return result;
    }
}