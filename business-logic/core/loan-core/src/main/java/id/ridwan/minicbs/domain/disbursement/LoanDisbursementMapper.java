package id.ridwan.minicbs.domain.disbursement;

import id.ridwan.minicbs.loan.account.disbursement.LoanDisbursement;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanDisbursementMapper {
    LoanDisbursement toEntity(LoanDisbursementDto dto);

    LoanDisbursementDto toDTO(LoanDisbursement entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LoanDisbursementDto dto, @MappingTarget LoanDisbursement entity);
}
