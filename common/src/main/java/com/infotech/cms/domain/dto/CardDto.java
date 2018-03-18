package com.infotech.cms.domain.dto;

import com.infotech.cms.domain.type.PersoState;
import com.infotech.cms.domain.type.Technology;

import java.time.LocalDate;

/**
 * Client input Data container, representing a minimalistic Card entity
 *
 * @author MohammadReza Alagheband
 */
public class CardDto {

    private Long cardTypeId;

    private Technology technology;
    private Integer serviceMask;
    private byte version;

    /*@JsonSerialize(using = CustomJsonLocalDateSerializer.class)
    @JsonDeserialize(using = CustomJsonLocalDateDeserializer.class)*/
    private LocalDate expirationDate;

    public LocalDate getExpirationDate() {
        return expirationDate;
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

    public Long getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Long cardTypeId) {
        this.cardTypeId = cardTypeId;
    }



    @Override
    public String toString() {
        return "CardDto{" +
                "cardTypeId='" + cardTypeId + '\'' +
                ", technology=" + technology +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
