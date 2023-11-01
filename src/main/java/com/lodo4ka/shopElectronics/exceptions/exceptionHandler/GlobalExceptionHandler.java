package com.lodo4ka.shopElectronics.exceptions.exceptionHandler;

import com.lodo4ka.shopElectronics.exceptions.customExceptions.ShopElectronicsException;
import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfo;
import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfoFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<List<ErrorInfo>> shopElectronicsException(ShopElectronicsException exception) {

        return new ResponseEntity<>(exception.getErrorInfoList(), exception.getErrorStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleRequestBodyValidationExceptions(MethodArgumentNotValidException e) {

        ShopElectronicsException exception = new ShopElectronicsException();

        ErrorInfo info = ErrorInfoFactory.getBadRequestErrorInfo("Invalid request body. " +
                "Make sure listed parameters are correct.");
        info.setCause(e);
        Map<String, Object> parameters = info.getParameters();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            parameters.put(fieldName, errorMessage);
        });

        exception.addErrorInfo(info);
        throw exception;
    }

    @ExceptionHandler()
    public void handleInvalidDateArgument(TypeMismatchException e) {

        ShopElectronicsException exception = new ShopElectronicsException();

        ErrorInfo info = ErrorInfoFactory.getBadRequestErrorInfo("Invalid request parameters. " +
                "Make sure all parameters are correct.");
        info.setCause(e);

        exception.addErrorInfo(info);
        throw exception;

    }
}
