package com.banking.payments.model.payments.types;

import com.banking.payments.model.payments.Payment;
import com.banking.payments.util.CurrencyUtil.Currency;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("3")
public class PaymentTypeThree extends Payment {
    @NotNull
    private String bicCode;

    public PaymentTypeThree(Currency currency, Double amount, String debtorIban, String creditorIban, String bicCode) {
        setAmount(amount);
        setDebtorIban(debtorIban);
        setCreditorIban(creditorIban);
        setCurrency(currency);
        setBicCode(bicCode);
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }
    public String getBicCode() {
        return this.bicCode;
    }
}
