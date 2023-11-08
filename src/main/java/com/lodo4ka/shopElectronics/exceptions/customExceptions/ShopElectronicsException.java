package com.lodo4ka.shopElectronics.exceptions.customExceptions;

import com.lodo4ka.shopElectronics.exceptions.info.ErrorInfo;
import com.lodo4ka.shopElectronics.exceptions.info.utils.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ShopElectronicsException extends RuntimeException {

    HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    ErrorType errorType = ErrorType.INTERNAL;

    List<ErrorInfo> errorInfoList = new ArrayList<>();

    public ShopElectronicsException() {
    }

    public ShopElectronicsException(ErrorType errorType, HttpStatus errorStatus) {
        this.errorType = errorType;
        this.errorStatus = errorStatus;
    }

    public void addErrorInfo(ErrorInfo errorInfo) {
        this.errorInfoList.add(errorInfo);
    }

    public ErrorInfo addErrorInfo() {
        ErrorInfo info = new ErrorInfo();
        this.errorInfoList.add(info);
        return info;
    }

    public List<ErrorInfo> getErrorInfoList() {
        return this.errorInfoList;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus) {
        this.errorStatus = errorStatus;
    }
}
