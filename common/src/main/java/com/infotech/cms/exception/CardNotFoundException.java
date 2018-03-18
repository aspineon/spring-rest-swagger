package com.infotech.cms.exception;

/**
 * raised when the requested Card is not available
 * @author MohammadReza Alagheband
 */
public class CardNotFoundException extends Exception {
    public CardNotFoundException() {
        super();
    }

    public CardNotFoundException(String message) {
        super(message);
    }
}
