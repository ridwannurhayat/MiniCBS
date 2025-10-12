package id.ridwan.minicbs.domain.repayment;

import id.ridwan.minicbs.loan.account.repayment.LoanRepayment;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanRepaymentMapper {
    LoanRepayment toEntity(LoanRepaymentDto dto);

    LoanRepaymentDto toDto(LoanRepayment entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LoanRepaymentDto dto, @MappingTarget LoanRepayment entity);
}
