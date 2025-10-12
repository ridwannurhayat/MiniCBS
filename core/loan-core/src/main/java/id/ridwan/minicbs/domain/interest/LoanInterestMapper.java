package id.ridwan.minicbs.domain.interest;

import id.ridwan.minicbs.loan.account.interest.LoanInterest;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanInterestMapper {
    LoanInterest toEntity(LoanInterestDto dto);

    LoanInterestDto toDto(LoanInterest entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LoanInterestDto dto, @MappingTarget LoanInterest entity);
}