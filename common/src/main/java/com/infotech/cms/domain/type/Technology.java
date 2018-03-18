package com.infotech.cms.domain.type;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * technology for card.
 *
 * @author Mohamamd Reza Alagheband
 */
public enum Technology {
    NORMAL("NORMAL"),
   EMULATED("EMULATED");

    private String value;

    Technology(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /*@JsonValue
    public String getHexCode() {
        return String.format("%02x", new Object[]{Byte.valueOf(this.code)});
    }*/
}
