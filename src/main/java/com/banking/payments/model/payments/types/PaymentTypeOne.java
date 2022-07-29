package com.banking.payments.model.payments.types;

import com.banking.payments.model.payments.Payment;
import com.banking.payments.util.CurrencyUtil.Currency;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@DiscriminatorValue("1")
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

    public void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return this.details;
    }
}
