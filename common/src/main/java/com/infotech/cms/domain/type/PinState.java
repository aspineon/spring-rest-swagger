package com.infotech.cms.domain.type;

/**
 * pin states for card.
 *
 * @author Mohamamd Reza Alagheband
 */
public enum PinState {
    INACTIVE("INACTIVE"),
    ACTIVE("ACTIVE");

    private String value;

    PinState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
