package com.infotech.cms.domain.dto;

import com.infotech.cms.domain.CardType;
import com.infotech.cms.domain.type.CardState;
import com.infotech.cms.domain.type.PersoState;
import com.infotech.cms.domain.type.PinState;
import com.infotech.cms.domain.type.Technology;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Card presentation container
 * @author MohammadReza Alagheband
 */
public class CardInfoResponse {
    private String pan;
    private String pin;
    private LocalDate expirationDate;
    private PersoState persoState;
    private CardState cardState;
    private PinState pinState;
    private Technology technology;
    private CardType cardType;
    private LocalDateTime issueDateTime;
    private LocalDateTime persoDateTime;
    private Integer serviceMask;
    private byte version;

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public PersoState getPersoState() {
        return persoState;
    }

    public void setPersoState(PersoState persoState) {
        this.persoState = persoState;
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }

    public PinState getPinState() {
        return pinState;
    }

    public void setPinState(PinState pinState) {
        this.pinState = pinState;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public LocalDateTime getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(LocalDateTime issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public LocalDateTime getPersoDateTime() {
        return persoDateTime;
    }

    public void setPersoDateTime(LocalDateTime persoDateTime) {
        this.persoDateTime = persoDateTime;
    }

    public Integer getServiceMask() {
        return serviceMask;
    }

    public void setServiceMask(Integer serviceMask) {
        this.serviceMask = serviceMask;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CardInfoResponse{" +
                "pan='" + pan + '\'' +
                ", pin='" + pin + '\'' +
                ", expirationDate=" + expirationDate +
                ", persoState=" + persoState +
                ", cardState=" + cardState +
                ", pinState=" + pinState +
                ", technology=" + technology +
                ", cardType=" + cardType +
                ", issueDateTime=" + issueDateTime +
                ", persoDateTime=" + persoDateTime +
                ", serviceMask=" + serviceMask +
                ", version=" + version +
                '}';
    }
}
