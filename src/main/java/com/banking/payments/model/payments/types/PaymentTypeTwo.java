package com.banking.payments.model.payments.types;

import com.banking.payments.model.payments.Payment;
import com.banking.payments.util.CurrencyUtil;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class PaymentTypeTwo extends Payment {
    private String details;

    public PaymentTypeTwo(Double amount, String debtorIban, String creditorIban, String details) {
        setAmount(amount);
        setDebtorIban(debtorIban);
        setCreditorIban(creditorIban);
        setCurrency(CurrencyUtil.Currency.USD);

        if (details != null) {
            setDetails(details);
        }
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return this.details;
    }
}
