package com.infotech.cms.domain;

import com.infotech.cms.domain.type.CardHolderState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * business object representing A Card Holder.
 *
 * @author MohammadReza Alagheband
 */
@Entity
@Table(name = "card_holder")
public class CardHolder extends BaseAuditableEntity {

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Size(max = 15)
    @Column(name = "cellPhone", nullable = false, length = 15)
    private String cellPhone; //TODO: convert to cellphone number

    //TODO: add CardHolderType , CardHolderState from sodexo
    @Column(name = "national_id_number", nullable = true, unique = true, length = 10)
    private String nationalIdNumber;

    @Size(max = 200)
    @Column(name = "address", nullable = true, length = 200)
    private String address;

    @Size(max = 50)
    @Column(name = "email", nullable = true, length = 50)
    private String email;


    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "card_holder_state", nullable = false, length = 50)
    private CardHolderState cardHolderState;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_card_holder_type", nullable = false)
    private CardHolderType cardHolderType;

    @OneToMany(mappedBy = "cardHolder",fetch = FetchType.LAZY)
    private Set<Card> cards = new HashSet<Card>();

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    public CardHolderState getCardHolderState() {
        return cardHolderState;
    }

    public void setCardHolderState(CardHolderState cardHolderState) {
        this.cardHolderState = cardHolderState;
    }

    public CardHolderType getCardHolderType() {
        return cardHolderType;
    }

    public void setCardHolderType(CardHolderType cardHolderType) {
        this.cardHolderType = cardHolderType;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) { 
        this.cards = cards;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return String.format("CardHolder[id:%d]", id);
    }
}
