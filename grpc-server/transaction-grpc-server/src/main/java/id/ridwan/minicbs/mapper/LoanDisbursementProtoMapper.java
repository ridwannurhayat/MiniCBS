package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.domain.disbursement.LoanDisbursementDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanDisbursementProtoMapper {
    LoanDisbursementDto toDto(LoanProto.LoanDisbursement proto);

    LoanProto.LoanDisbursement toProto(LoanDisbursementDto dto);

    List<LoanProto.LoanDisbursement> toProtoList(List<LoanDisbursementDto> dto);


}
