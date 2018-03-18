package com.infotech.cms.web.rest;

import com.infotech.cms.exception.*;
import com.infotech.cms.web.rest.dto.BaseResponse;
import com.infotech.cms.web.rest.util.ValidationError;
import com.infotech.cms.web.rest.util.ValidationErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.http.HTTPException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing custom errors
 * @author MohammadReza Alagheband
 */

@ControllerAdvice
public class ApplicationExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationExceptionHandler.class);


    /**
     * Once given requested Json data contains not supported fields or
     * is completely out of structure this exception will be raised ,
     * the execution point redirected to the following method so that
     * response properly set for the client to be informed
     * @param e
     * @return  BaseReposnse Containing the problem reason
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler
    public ResponseEntity<BaseResponse> handlebadRequest(HttpMessageNotReadableException e) {

        LOG.warn("Returning HTTP 400 Bad Request: {}",e);
        BaseResponse response = new BaseResponse();
        response.setMessage("Malformed Http Message.");
        response.setCode(BaseResponse.BAD_REQUEST);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * will be triggered on webservices calling problems
     *
     * @param e
     * @return BaseReposnse Containing the problem reason
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleWebserviceError(HTTPException e) {

        LOG.error("WebService Connection error: {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("WebService Error "+e.getStatusCode()+" "+e.getMessage());
        response.setCode(BaseResponse.WS_ERROR);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(e.getStatusCode()).body(response);
    }

    /**
     * will be triggered on date time parsing problems
     *
     * @param e
     * @return BaseReposnse Containing the problem reason
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleWebserviceError(DateTimeParseException e) {

        LOG.error("DateTimeParseException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("date time parse error");
        response.setCode(BaseResponse.BAD_REQUEST);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * will be triggered on text parsing issues
     *
     * @param e
     * @return BaseReposnse Containing the problem reason
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleWebserviceError(ParseException e) {

        LOG.error("ParseException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("parse error");
        response.setCode(BaseResponse.BAD_REQUEST);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * validation error once binded parameter are not valid
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 200
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<BaseResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {


        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }

        BaseResponse response = new BaseResponse();
        response.setMessage("validation error");
        response.setErrors(errors);
        response.setCode(BaseResponse.VALIDATION_ERROR);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * will be triggered once given cardHolder not available
     *
     * @param e
     * @return BaseReposnse Containing the problem reason
     */
    @ResponseStatus(HttpStatus.OK)  // 200
    @ExceptionHandler
    public ResponseEntity<BaseResponse> cardHolderNotfound(CardHolderNotFoundException e) {

        LOG.debug("CardHolderNotFoundException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("corresponding cardholder not found");
        response.setCode(BaseResponse.RESOURCE_NOT_FOUND);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * will be triggered once given card not available
     *
     * @param e
     * @return BaseReposnse Containing the problem reason
     */
    @ResponseStatus(HttpStatus.OK)  // 200
    @ExceptionHandler
    public ResponseEntity<BaseResponse> cardNotfound(CardNotFoundException e) {

        LOG.debug("CardNotFoundException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("corresponding card not found");
        response.setCode(BaseResponse.RESOURCE_NOT_FOUND);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * will be triggered once given card type not available
     *
     * @param e
     * @return BaseReposnse Containing the problem reason
     */
    @ResponseStatus(HttpStatus.OK)  // 200
    @ExceptionHandler
    public ResponseEntity<BaseResponse> cardTypeNotfound(CardTypeNotFoundException e) {

        LOG.debug("CardTypeNotFoundException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("corresponding card type not found");
        response.setCode(BaseResponse.RESOURCE_NOT_FOUND);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    /**
     * will be triggered once given account type not available
     *
     * @param e
     * @return BaseReposnse Containing the problem reason
     */
    @ResponseStatus(HttpStatus.OK)  // 200
    @ExceptionHandler
    public ResponseEntity<BaseResponse> accountTypeNotfound(AccountTypeNotFoundException e) {

        LOG.debug("AccountTypeNotFoundException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("corresponding account type not found");
        response.setCode(BaseResponse.RESOURCE_NOT_FOUND);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * once the required card is already assigned to someone else this
     * exeception will be raised
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)  // 404
    @ExceptionHandler
    public ResponseEntity<BaseResponse> cardNotAssignable(CardNotAssignableException e) {

        LOG.debug("CardNotAssignableException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("card not assignable");
        response.setCode(BaseResponse.BAD_REQUEST);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     *  when providing pan number for a service is mandatory
     *  and it's not given , this occurred
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)  // 200
    @ExceptionHandler
    public ResponseEntity<BaseResponse> noPanNumberProvided(PanNumberNotProvidedException e) {

        LOG.debug("PanNumberNotProvidedException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("no pan number provided");
        response.setCode(BaseResponse.BAD_REQUEST);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     *  when it's not possible to generate pan
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)  // 200
    @ExceptionHandler
    public ResponseEntity<BaseResponse> panOverflowException(PanOverflowException e) {

        LOG.debug("PanOverflowException : {}", e);
        BaseResponse response = new BaseResponse();
        response.setMessage("pan number capacity is over");
        response.setCode(BaseResponse.SERVER_ERROR);
        response.setSuccess(false);
        response.setResponseId(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





}
