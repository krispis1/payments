package com.banking.payments.repository;

import com.banking.payments.model.payments.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
