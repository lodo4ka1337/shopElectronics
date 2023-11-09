package com.lodo4ka.shopElectronics.persistance.model.DTO.dateValidation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class DateValidatorUsingLocalDate implements DateValidator {

    @Override
    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}