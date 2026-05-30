package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.domain.account.LoanAccountDto;
import id.ridwan.minicbs.grpc.LoanProto;
import org.mapstruct.*;

@Mapper(
        componentModel = "cdi",
        uses = {
                LoanDisbursementProtoMapper.class,
                LoanInstallmentProtoMapper.class,
                LoanInterestProtoMapper.class,
                LoanRepaymentProtoMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LoanAccountProtoMapper {

    LoanAccountDto toDto(LoanProto.LoanAccount proto);

    LoanProto.LoanAccount toProto(LoanAccountDto dto);
}