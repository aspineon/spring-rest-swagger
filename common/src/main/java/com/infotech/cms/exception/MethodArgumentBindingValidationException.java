package com.infotech.cms.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Iterator;

/**
 * Exception Wrapper for the moment of validation error
 * @author MohammadReza Alagheband
 */
public class MethodArgumentBindingValidationException  extends Exception  {
    private final BindingResult bindingResult;

    public MethodArgumentBindingValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }

}
