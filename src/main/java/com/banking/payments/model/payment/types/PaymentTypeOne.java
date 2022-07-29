package com.banking.payments.model.payment.types;

import com.banking.payments.model.payment.Payment;
import com.banking.payments.util.CurrencyUtil.Currency;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("0")
public class PaymentTypeOne extends Payment {
    @NotNull
    private String details;

    public PaymentTypeOne(Double amount, String debtorIban, String creditorIban, String details) {
        setAmount(amount);
        setDebtorIban(debtorIban);
        setCreditorIban(creditorIban);
        setCurrency(Currency.EUR);
        setDetails(details);
    }

    private void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return this.details;
    }

    private PaymentTypeOne() {}
}
