package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.domain.interest.LoanInterestDto;
import org.mapstruct.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanInterestProtoMapper {
    LoanInterestDto toDto(LoanProto.LoanInterest proto);

    LoanProto.LoanInterest toProto(LoanInterestDto dto);


}
