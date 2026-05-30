package id.ridwan.minicbs.service;

import com.google.protobuf.Empty;
import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.grpc.MutinyLoanAccountServiceGrpc;
import id.ridwan.minicbs.mapper.LoanAccountProtoMapper;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
@GrpcService
public class LoanAccountServiceImpl extends MutinyLoanAccountServiceGrpc.LoanAccountServiceImplBase {

    @Inject
    LoanAccountService service;

    @Inject
    LoanAccountProtoMapper mapper;

    @Override
    public Uni<LoanProto.LoanAccount> create(LoanProto.CreateRequest request) {
        return service.create(mapper.toDto(request.getLoanAccount()))
                .onItem().transform(mapper::toProto);
    }

    @Override
    public Uni<LoanProto.LoanAccount> update(LoanProto.UpdateRequest request) {
        return service.update(UUID.fromString(request.getId()), mapper.toDto(request.getLoanAccount()))
                .onItem().transform(mapper::toProto);
    }

    @Override
    public Uni<Empty> delete(LoanProto.DeleteRequest request) {
        return service.delete(UUID.fromString(request.getId()))
                .replaceWith(Empty.newBuilder().build());
    }
}
