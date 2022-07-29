package com.banking.payments.repository;

import com.banking.payments.model.payment.cancellation.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {
}
