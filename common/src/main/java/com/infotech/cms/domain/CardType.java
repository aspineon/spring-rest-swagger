package com.infotech.cms.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.infotech.cms.util.ByteToHexStringSerializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * simple business object representing card type.
 *
 * @author Mohamamd Reza Alagheband
 *
 */
@Entity
@Table(name="card_type")
public class CardType extends BaseNamedEntity {

    @NotNull
    @Column(name = "code", nullable = false)
    private byte code;



    @Override
    public String toString() {
        return String.format("CardType[id:%d, name:%s]", id, name);
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardType)) {
            return false;
        }        
        CardType cardType = (CardType)other; 
        if (this.id == cardType.id) {
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
