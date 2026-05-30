package id.ridwan.minicbs.domain.interest;

import java.math.BigDecimal;
import java.util.UUID;

public record LoanInterestDto(
        UUID id,
        BigDecimal rate
) {}
