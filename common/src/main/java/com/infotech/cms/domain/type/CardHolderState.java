package com.infotech.cms.domain.type;

/**
 * card holder states.
 *
 * @author Mohamamd Reza Alagheband
 */
public enum CardHolderState {
    ACTIVE("ACTIVE");

    private String value;

    CardHolderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
