package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.loan.account.installment.LoanInstallment;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface LoanInstallmentProtoMapper {
    LoanInstallment toEntity(LoanProto.LoanInstallment proto);

    LoanProto.LoanInstallment toProto(LoanInstallment entity);

    List<LoanProto.LoanInstallment> toProtoList(List<LoanInstallment> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromProto(LoanProto.LoanInstallment dto, @MappingTarget LoanInstallment entity);
}
