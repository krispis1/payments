package com.banking.payments.model.payment.types;

import com.banking.payments.model.payment.Payment;
import com.banking.payments.util.CurrencyUtil;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
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

    private void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return this.details;
    }

    private PaymentTypeTwo() {}
}
