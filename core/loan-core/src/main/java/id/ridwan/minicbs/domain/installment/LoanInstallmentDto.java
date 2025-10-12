package id.ridwan.minicbs.domain.installment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanInstallmentDto(
        UUID id,
        LocalDate installmentDate,
        BigDecimal amount,
        BigDecimal principal,
        BigDecimal interest
) {}