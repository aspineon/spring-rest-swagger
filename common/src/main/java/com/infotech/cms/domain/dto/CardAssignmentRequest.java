package com.infotech.cms.domain.dto;

import com.infotech.accounting.domain.Account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * data container to hold card assignment data
 *
 * @author MohammadReza Alagheband
 *
 */
public class CardAssignmentRequest  extends CardDto {
    @NotNull
    private Long cardHolderId;

    @NotNull
    private Long accountTypeId;

    @NotNull
    @Size(min = 8,max = 20)
    private String accountNumber;

    private BigDecimal initBalance =new BigDecimal("0");


    public Long getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(Long cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getInitBalance() {
        return initBalance;
    }

    public void setInitBalance(BigDecimal initBalance) {
        this.initBalance = initBalance;
    }

    @Override
    public String toString() {
        return "CardAssignmentRequest{" +
                "cardHolderId=" + cardHolderId +
                '}';
    }
}
