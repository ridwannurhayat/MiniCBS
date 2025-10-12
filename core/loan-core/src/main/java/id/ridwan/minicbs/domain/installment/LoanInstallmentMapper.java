package id.ridwan.minicbs.domain.installment;

import id.ridwan.minicbs.loan.account.installment.LoanInstallment;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface LoanInstallmentMapper {

    LoanInstallment toEntity(LoanInstallmentDto dto);

    LoanInstallmentDto toDto(LoanInstallment entity);

    List<LoanInstallmentDto> toDtoList(List<LoanInstallment> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LoanInstallmentDto dto, @MappingTarget LoanInstallment entity);
}