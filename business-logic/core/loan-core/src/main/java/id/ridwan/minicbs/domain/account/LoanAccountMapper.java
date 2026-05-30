package id.ridwan.minicbs.domain.account;

import id.ridwan.minicbs.domain.disbursement.LoanDisbursementMapper;
import id.ridwan.minicbs.domain.installment.LoanInstallmentMapper;
import id.ridwan.minicbs.domain.interest.LoanInterestMapper;
import id.ridwan.minicbs.domain.repayment.LoanRepaymentMapper;
import id.ridwan.minicbs.loan.account.LoanAccount;
import org.mapstruct.*;

@Mapper(
        componentModel = "cdi",
        uses = {
                LoanDisbursementMapper.class,
                LoanInstallmentMapper.class,
                LoanInterestMapper.class,
                LoanRepaymentMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LoanAccountMapper {

    LoanAccount toEntity(LoanAccountDto dto);

    LoanAccountDto toDto(LoanAccount entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LoanAccountDto dto, @MappingTarget LoanAccount entity);
}