package com.infotech.cms.domain;

import com.infotech.accounting.domain.Account;
import com.infotech.cms.domain.type.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * business object representing A Card.
 *
 * @author Mohamamd Reza Alagheband
 */
@Entity
@Table(name = "card")
public class Card extends BaseAuditableEntity {

    @NotNull
    @Size(min = 16, max = 16)
    @Column(name = "pan", nullable = false, length = 16, unique = true)
    private String pan;

    @Size(min = 4, max = 4)
    @Column(name = "pin", nullable = true, length = 4)
    private String pin;

    @Column(name = "expiration_date", nullable = true)
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "perso_state", nullable = false, length = 50)
    private PersoState persoState=PersoState.NOT_PERSONALIZED;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "card_state", nullable = false, length = 50)
    private CardState cardState;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "pin_state", nullable = false, length = 50)
    private PinState pinState;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "technology", nullable = false)
    private Technology technology;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_card_holder", nullable = false)
    private CardHolder cardHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account", nullable = true)
    private Account account;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_card_type", nullable = false)
    private CardType cardType;

    @NotNull
    @Column(name = "issue_date_time", nullable = false)
    private LocalDateTime issueDateTime;

    @Column(name = "perso_date_time", nullable = true)
    private LocalDateTime persoDateTime;

    @NotNull
    @Column(name = "version", nullable = false)
    private byte version= Byte.decode("0x01");

    @NotNull
    @Column(name = "service_mask", nullable = false)
    private Integer serviceMask= Integer.decode("0x00000200");


    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public Integer getServiceMask() {
        return serviceMask;
    }

    public void setServiceMask(Integer serviceMask) {
        this.serviceMask = serviceMask;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CardHolder getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }


    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
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

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

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

    public PersoState getPersoState() {
        return persoState;
    }

    public void setPersoState(PersoState persoState) {
        this.persoState = persoState;
    }

    @Override
    public String toString() {
        return String.format("Card[id:%d, pan: %s]", id, pan);
    }
}
