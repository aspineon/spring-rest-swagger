package com.infotech.cms.exception;

/**
 * raised when the requested Card is not assignable
 * @author MohammadReza Alagheband
 */
public class CardNotAssignableException extends Exception {
    public CardNotAssignableException() {
        super();
    }

    public CardNotAssignableException(String message) {
        super(message);
    }
}
