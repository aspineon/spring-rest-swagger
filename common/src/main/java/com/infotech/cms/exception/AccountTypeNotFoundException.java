package com.infotech.cms.exception;

/**
 * raised when the requested Card is not available
 * @author MohammadReza Alagheband
 */
public class AccountTypeNotFoundException extends Exception {
    public AccountTypeNotFoundException() {
        super();
    }

    public AccountTypeNotFoundException(String message) {
        super(message);
    }
}
