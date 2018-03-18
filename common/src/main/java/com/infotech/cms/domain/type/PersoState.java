package com.infotech.cms.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * personalization states and code for card.
 *
 * @author Mohamamd Reza Alagheband
 */
public enum PersoState {

    NOT_PERSONALIZED("NOT_PERSONALIZED"),
    PERSONALIZED("PERSONALIZED");


    private String value;

    PersoState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
