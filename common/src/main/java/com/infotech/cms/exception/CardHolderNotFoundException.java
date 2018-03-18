package com.infotech.cms.exception;

/**
 * raised when the requested CardHolder is not available
 * @author MohammadReza Alagheband
 */
public class CardHolderNotFoundException extends Exception {
    public CardHolderNotFoundException() {
        super();
    }

    public CardHolderNotFoundException(String message) {
        super(message);
    }
}
