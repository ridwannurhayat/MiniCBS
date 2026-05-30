package id.ridwan.minicbs.domain.repayment;

import id.ridwan.minicbs.loan.component.repayment.RepaymentPeriod;
import id.ridwan.minicbs.loan.component.repayment.Repayment.RepaymentMethod;
import java.util.UUID;

public record LoanRepaymentDto(
        UUID id,
        RepaymentMethod repaymentMethod,
        Integer repaymentDay,
        RepaymentPeriod repaymentPeriod
) {}
