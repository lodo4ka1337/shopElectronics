package com.lodo4ka.shopElectronics.exceptions.info;

import com.lodo4ka.shopElectronics.exceptions.info.utils.ErrorSeverity;

import java.util.Map;

public class ErrorInfoFactory {
    public static ErrorInfo getBadRequestErrorInfo(String userErrorDescription) {
        ErrorInfo info = new ErrorInfo();
        info.setSeverity(ErrorSeverity.ERROR);
        info.setUserErrorDescription(userErrorDescription);
        info.setErrorDescription("Incorrect user request input.");
        return info;
    }

    public static ErrorInfo getInvalidDateErrorInfo(String datePattern, Map<String, Object> dateParameters) {
        String userErrorDescription = "Invalid creation date request parameters. " +
                "Make sure all date parameters follow the pattern '" + datePattern + "'.";
        ErrorInfo info = getBadRequestErrorInfo(userErrorDescription);
        Map<String, Object> parameters = info.getParameters();
        parameters.putAll(dateParameters);
        return info;
    }
}
