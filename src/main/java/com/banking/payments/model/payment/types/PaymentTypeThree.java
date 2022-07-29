package com.banking.payments.model.payment.types;

import com.banking.payments.model.payment.Payment;
import com.banking.payments.util.CurrencyUtil.Currency;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("2")
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

    private void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }
    public String getBicCode() {
        return this.bicCode;
    }

    private PaymentTypeThree() {}
}
