package com.lodo4ka.shopElectronics.exceptions.exceptionHandler;

import com.lodo4ka.shopElectronics.exceptions.customExceptions.ShopElectronicsException;
import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfo;
import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfoFactory;
import com.lodo4ka.shopElectronics.exceptions.info.utils.ErrorType;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ErrorInfo>> handleRequestBodyValidationExceptions(MethodArgumentNotValidException e) {

        ShopElectronicsException exception = new ShopElectronicsException();
        exception.setErrorStatus(HttpStatus.BAD_REQUEST);
        exception.setErrorType(ErrorType.CLIENT);

        ErrorInfo info = ErrorInfoFactory.getBadRequestErrorInfo("Invalid request body. " +
                "Make sure listed parameters are correct.");
        Map<String, Object> parameters = info.getParameters();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            parameters.put(fieldName, errorMessage);
        });

        exception.addErrorInfo(info);
        return new ResponseEntity<>(exception.getErrorInfoList(), exception.getErrorStatus());
    }

    @ExceptionHandler()
    public void handleInvalidDateArgument(TypeMismatchException e) {

        ShopElectronicsException exception = new ShopElectronicsException();

        ErrorInfo info = ErrorInfoFactory.getBadRequestErrorInfo("Invalid request parameters. " +
                "Make sure all parameters are correct.");

        exception.addErrorInfo(info);
        throw exception;

    }
}
