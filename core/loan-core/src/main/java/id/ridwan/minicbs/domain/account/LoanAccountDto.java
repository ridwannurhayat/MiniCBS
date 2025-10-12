package id.ridwan.minicbs.domain.account;


import id.ridwan.minicbs.domain.disbursement.LoanDisbursementDto;
import id.ridwan.minicbs.domain.installment.LoanInstallmentDto;
import id.ridwan.minicbs.domain.interest.LoanInterestDto;
import id.ridwan.minicbs.domain.repayment.LoanRepaymentDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record LoanAccountDto(
        UUID id,
        LocalDate dueDate,
        LoanInterestDto interest,
        LoanRepaymentDto repayment,
        List<LoanDisbursementDto> disbursements,
        List<LoanInstallmentDto> installments
) {}
