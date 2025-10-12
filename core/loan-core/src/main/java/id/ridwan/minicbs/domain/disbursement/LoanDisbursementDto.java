package id.ridwan.minicbs.domain.disbursement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanDisbursementDto(
        UUID id,
        LocalDate disbursementDate,
        BigDecimal amount
) {}
