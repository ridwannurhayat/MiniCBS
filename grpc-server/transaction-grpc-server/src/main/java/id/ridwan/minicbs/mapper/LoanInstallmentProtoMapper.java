package id.ridwan.minicbs.mapper;

import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.domain.installment.LoanInstallmentDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface LoanInstallmentProtoMapper {
    LoanInstallmentDto toDto(LoanProto.LoanInstallment proto);

    LoanProto.LoanInstallment toProto(LoanInstallmentDto dto);

    List<LoanProto.LoanInstallment> toProtoList(List<LoanInstallmentDto> dtos);


}
