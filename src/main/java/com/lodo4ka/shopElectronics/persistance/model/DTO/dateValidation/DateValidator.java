package com.lodo4ka.shopElectronics.persistance.model.DTO.dateValidation;

public interface DateValidator {

    static final String ISO_LOCAL_DATE_PATTERN = "yyyy-MM-dd";

    boolean isValid(String dateStr);
}