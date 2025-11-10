package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.loan.account.LoanAccount;
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

    LoanAccount toEntity(LoanProto.LoanAccount proto);

    LoanProto.LoanAccount toProto(LoanAccount entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromProto(LoanProto.LoanAccount proto, @MappingTarget LoanAccount entity);
}