package com.example.demo2.web.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice({"com.example.demo2"})
public class StoreRestControllerAdvice {

        @ExceptionHandler(value = NotAuthenticatedException.class)
        public ResponseEntity<AdviceErrorMessage> handleNotAuthenticatedException(NotAuthorizedException e) {
        log.trace("REST call missing/invalid authentication: " + e.getMessage(), this.getClass());
        return new ResponseEntity<>(new AdviceErrorMessage(e), HttpStatus.UNAUTHORIZED);
    }

        @ExceptionHandler(value = NotAuthorizedException.class)
        public ResponseEntity<AdviceErrorMessage> handleNotAuthorizedException(NotAuthorizedException e) {
          log.trace("REST call without the proper rights: " + e.getMessage(), this.getClass());
            return new ResponseEntity<>(new AdviceErrorMessage(e), HttpStatus.UNAUTHORIZED);
        }

        @ExceptionHandler(value = NotFoundException.class)
        public ResponseEntity<AdviceErrorMessage> handleNotFoundException(NotFoundException e) {
            log.trace("Object not found: " + e.getMessage(), this.getClass());
            return new ResponseEntity<>(new AdviceErrorMessage(e), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = ParameterException.class)
        public  ResponseEntity<AdviceErrorMessage> handleParameterException(ParameterException e) {
            log.trace("Missing parameters : " + e.getMessage(), this.getClass());
            return new ResponseEntity<>(new AdviceErrorMessage(e), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = Throwable.class)
        public ResponseEntity<AdviceErrorMessage> handleGenericException(Throwable e) {
            log.trace("Unhandled Exception " + e.getMessage(), this.getClass());
            return new ResponseEntity<>(new AdviceErrorMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
