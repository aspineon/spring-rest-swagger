package com.infotech.cms.exception;

/**
 * raised when no pan provided when it's necessary
 * @author MohammadReza Alagheband
 */
public class PanNumberNotProvidedException extends Exception {
    public PanNumberNotProvidedException() {
        super();
    }

    public PanNumberNotProvidedException(String message) {
        super(message);
    }
}
