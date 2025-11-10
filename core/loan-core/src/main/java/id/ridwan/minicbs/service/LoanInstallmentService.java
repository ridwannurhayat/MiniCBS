package id.ridwan.minicbs.service;

import id.ridwan.minicbs.domain.installment.LoanInstallmentDto;
import id.ridwan.minicbs.domain.installment.LoanInstallmentMapper;
import id.ridwan.minicbs.loan.account.installment.LoanInstallment;
import id.ridwan.minicbs.loan.account.installment.LoanInstallmentRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@ApplicationScoped
public class LoanInstallmentService {

    @Inject
    LoanInstallmentRepository repository;

    @Inject
    LoanInstallmentMapper mapper;

    public Uni<List<LoanInstallmentDto>> findAllByLoanAccountId(UUID loanAccountId) {

        return repository.findByLoanAccountId(loanAccountId)
                .onItem().transform(listOfLoanInstallment ->
                    listOfLoanInstallment.stream()
                            .map(mapper::toDto)
                            .toList()
                );
    }

    public Uni<Map<String, Object>> findPagedAndFiltered(
            UUID loanAccountId,
            LocalDate fromDate,
            LocalDate toDate,
            Integer page,
            Integer size
    ) {
        var query = repository.findPagedAndFiltered(loanAccountId, fromDate, toDate, page, size);

        return query.list().onItem().transform((listOfLoanInstallment) -> {
            List<LoanInstallmentDto> content = listOfLoanInstallment.stream()
                    .map(mapper::toDto)
                    .toList();

            Map<String, Object> temp = new HashMap<>();
            temp.put("content", content);
            temp.put("page", page == null ? 0 : page);
            temp.put("size", size == null ? content.size() : size);
            temp.put("totalElements", query.count());
            temp.put("totalPages", query.pageCount());
            return temp;
        });
    }

    public Uni<LoanInstallmentDto> findById(UUID id) {
        return this.findById(id, mapper::toDto);
    }

    @Transactional
    public Uni<LoanInstallmentDto> create(LoanInstallmentDto dto) {
        LoanInstallment entity = mapper.toEntity(dto);
        return repository.persist(entity)
                .onItem().ifNotNull().transform(mapper::toDto)
                .onItem().ifNull().failWith(() -> new NotFoundException("LoanInstallment not created"));
    }

    @Transactional
    public Uni<LoanInstallmentDto> update(UUID id, LoanInstallmentDto dto) {
        return this.findById(id, entity -> {
            mapper.updateEntityFromDto(dto, entity);
            return mapper.toDto(entity);
        });
    }

    @Transactional
    public Uni<Void> delete(UUID id) {
        return this.findById(id, (entity) -> {
            entity.setActive(false);
            return entity;
        }).replaceWithVoid();
    }

    private <T> Uni<T> findById(UUID id, Function<LoanInstallment, T> func) {
        return repository.findById(id)
                .onItem().ifNotNull().transform(func)
                .onItem().ifNull().failWith(() -> new NotFoundException("LoanInstallment not found"));
    }
}
