package com.infotech.cms.exception;

/**
 * raised when the requested CardType is not available
 * @author MohammadReza Alagheband
 */
public class CardTypeNotFoundException extends Exception {
    public CardTypeNotFoundException() {
        super();
    }

    public CardTypeNotFoundException(String message) {
        super(message);
    }
}
