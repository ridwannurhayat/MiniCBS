package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.loan.account.repayment.LoanRepayment;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanRepaymentProtoMapper {
    LoanRepayment toEntity(LoanProto.LoanRepayment proto);

    LoanProto.LoanRepayment toProto(LoanRepayment entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromProto(LoanProto.LoanRepayment proto, @MappingTarget LoanRepayment entity);
}
