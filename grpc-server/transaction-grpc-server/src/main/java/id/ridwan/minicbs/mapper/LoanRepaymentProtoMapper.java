package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.domain.repayment.LoanRepaymentDto;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanRepaymentProtoMapper {

    @ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.NULL)
    LoanRepaymentDto toDto(LoanProto.LoanRepayment proto);

    LoanProto.LoanRepayment toProto(LoanRepaymentDto dto);


}
