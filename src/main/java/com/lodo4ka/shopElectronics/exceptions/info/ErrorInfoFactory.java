package com.lodo4ka.shopElectronics.exceptions.info;

import com.lodo4ka.shopElectronics.exceptions.info.utils.ErrorSeverity;
import com.lodo4ka.shopElectronics.exceptions.info.utils.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class ErrorInfoFactory {
    public static ErrorInfo getBadRequestErrorInfo(String userErrorDescription) {
        ErrorInfo info = new ErrorInfo();
        info.setHttpStatus(HttpStatus.BAD_REQUEST);
        info.setErrorType(ErrorType.CLIENT);
        info.setSeverity(ErrorSeverity.ERROR);
        info.setUserErrorDescription(userErrorDescription);
        info.setErrorDescription("Incorrect user request input.");
        return info;
    }

    public static ErrorInfo getInvalidDateErrorInfo(String datePattern, List<String> dateParameters) {
        String userErrorDescription = "Invalid creation date request parameters. " +
                "Make sure all date parameters follow the pattern '" + datePattern + "'.";
        ErrorInfo info = getBadRequestErrorInfo(userErrorDescription);
        Map<String, Object> parameters = info.getParameters();
        dateParameters.forEach(dateParameter -> parameters.put("date", dateParameter));
        return info;
    }
}
