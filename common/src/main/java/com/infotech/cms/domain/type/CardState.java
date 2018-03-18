package com.infotech.cms.domain.type;

/**
 * states for card.
 *
 * @author Mohamamd Reza Alagheband
 */
public enum CardState {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED"),
    SUSPENDED("SUSPENDED"),
    TERMINATED("TERMINATED");

    private String value;

    CardState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
