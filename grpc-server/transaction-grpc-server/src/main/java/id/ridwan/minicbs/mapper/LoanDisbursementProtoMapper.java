package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.loan.account.disbursement.LoanDisbursement;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanDisbursementProtoMapper {
    LoanDisbursement toEntity(LoanProto.LoanDisbursement proto);

    LoanProto.LoanDisbursement toProto(LoanDisbursement entity);

    List<LoanProto.LoanDisbursement> toProtoList(List<LoanDisbursement> entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromProto(LoanProto.LoanDisbursement proto, @MappingTarget LoanDisbursement entity);
}
