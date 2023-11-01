package com.lodo4ka.shopElectronics.exceptions.info;

import com.lodo4ka.shopElectronics.exceptions.info.utils.ErrorSeverity;
import com.lodo4ka.shopElectronics.exceptions.info.utils.ErrorType;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorInfo {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private HttpStatus httpStatus;

    private Throwable cause;

    private ErrorType errorType;

    private ErrorSeverity severity;

    private String userErrorDescription;

    private String errorDescription;

    private String errorCorrection;

    private Map<String, Object> parameters = new HashMap<>();

    public ErrorInfo() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(ErrorSeverity severity) {
        this.severity = severity;
    }

    public String getUserErrorDescription() {
        return userErrorDescription;
    }

    public void setUserErrorDescription(String userErrorDescription) {
        this.userErrorDescription = userErrorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorCorrection() {
        return errorCorrection;
    }

    public void setErrorCorrection(String errorCorrection) {
        this.errorCorrection = errorCorrection;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}