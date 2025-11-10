package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.loan.account.interest.LoanInterest;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanInterestProtoMapper {
    LoanInterest toEntity(LoanProto.LoanInterest proto);

    LoanProto.LoanInterest toProto(LoanInterest entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromProto(LoanProto.LoanInterest proto, @MappingTarget LoanInterest entity);
}
