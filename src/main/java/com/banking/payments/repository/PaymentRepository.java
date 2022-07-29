package com.banking.payments.repository;

import com.banking.payments.enums.payment.PaymentType;
import com.banking.payments.model.payment.Payment;
import com.banking.payments.enums.payment.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByPaymentIdAndStatus(Integer paymentId, PaymentStatus status);

    @Query(value = "SELECT payments.payment_type FROM payments.payments WHERE payments.payment_id = :paymentId", nativeQuery = true)
    PaymentType getPaymentTypeById(@Param("paymentId") Integer paymentId);
}
