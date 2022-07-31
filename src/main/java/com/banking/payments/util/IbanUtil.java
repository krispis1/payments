package com.banking.payments.util;

import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.routines.IBANValidator;

public class IbanUtil {
    public static void validate(String code) throws ValidatorException {
        if (code == null)
            return;

        if (!IBANValidator.DEFAULT_IBAN_VALIDATOR.isValid(code)) {
            throw new ValidatorException("IBAN is not valid.");
        }
    }
}
