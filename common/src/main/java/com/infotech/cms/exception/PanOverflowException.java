package com.infotech.cms.exception;

/**
 * raised when the requested Pan capacity is over
 * @author MohammadReza Alagheband
 */
public class PanOverflowException extends Exception {
    public PanOverflowException() {
        super();
    }

    public PanOverflowException(String message) {
        super(message);
    }
}
