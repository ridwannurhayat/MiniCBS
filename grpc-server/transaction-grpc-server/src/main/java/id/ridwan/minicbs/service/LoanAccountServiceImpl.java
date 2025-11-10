package id.ridwan.minicbs.service;

import com.google.protobuf.Empty;
import id.ridwan.minicbs.grpc.LoanProto;
import id.ridwan.minicbs.grpc.MutinyLoanAccountServiceGrpc;
import id.ridwan.minicbs.loan.account.LoanAccount;
import id.ridwan.minicbs.loan.account.LoanAccountRepository;
import id.ridwan.minicbs.mapper.LoanAccountProtoMapper;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;
import java.util.function.Function;

@Singleton
public class LoanAccountServiceImpl extends MutinyLoanAccountServiceGrpc.LoanAccountServiceImplBase {

    @Inject
    private LoanAccountRepository repository;

    @Inject
    private LoanAccountProtoMapper mapper;

    public Uni<LoanProto.LoanAccount> create(LoanProto.CreateRequest request) {
        LoanAccount entity = mapper.toEntity(request.getLoanAccount());
        return repository.persist(entity).onItem().transform(mapper::toProto);
    }

    public Uni<LoanProto.LoanAccount> update(LoanProto.UpdateRequest request) {
        return repository.findById(UUID.fromString(request.getId()))
                .onItem().ifNull().failWith(EntityNotFoundException::new)
                .onItem().ifNotNull().transform(entity -> {
                    mapper.updateEntityFromProto(request.getLoanAccount(), entity);
                    return entity;
                })
                .onItem().transform(mapper::toProto);
    }

    public Uni<Empty> delete(LoanProto.DeleteRequest request) {
        return this.findById(UUID.fromString(request.getId()), (entity) -> {
            entity.setActive(false);
            return entity;
        }).replaceWith(Uni.createFrom().item(Empty.newBuilder().build()));
    }

    private <T> Uni<T> findById(UUID id, Function<LoanAccount, T> func) {
        return repository.findById(id)
                .onItem().ifNotNull().transform(func)
                .onItem().ifNull().failWith(() -> new EntityNotFoundException("LoanAccount not found"));
    }
}
