package io.zipcoder.tc_spring_poll_application.exception;

import io.zipcoder.tc_spring_poll_application.error.ErrorDetail;
import io.zipcoder.tc_spring_poll_application.error.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request){
        ErrorDetail ed = new ErrorDetail("Not found!",123,"not found!!!!!!!!",new Date().getTime(),":(");
        return new ResponseEntity<>(ed,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetail>
    handleValidationError(  MethodArgumentNotValidException manve,
                            HttpServletRequest request){

        ErrorDetail errorDetail = new ErrorDetail("Not found?",111,"?",new Date().getTime(),"?????");
        List<FieldError> fieldErrors =  manve.getBindingResult().getFieldErrors();
        ValidationError ve = new ValidationError();

        for(FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
            if(validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage("this lab is bad");
            validationErrorList.add(validationError);
        }

        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }


}
