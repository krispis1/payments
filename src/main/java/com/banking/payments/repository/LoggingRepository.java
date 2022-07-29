package com.banking.payments.repository;

import com.banking.payments.model.logging.Logging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingRepository extends JpaRepository<Logging, Integer> {
}
