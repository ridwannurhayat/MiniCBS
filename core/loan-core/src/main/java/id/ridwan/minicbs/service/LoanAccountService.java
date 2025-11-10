package id.ridwan.minicbs.service;

import id.ridwan.minicbs.domain.account.LoanAccountDto;
import id.ridwan.minicbs.domain.account.LoanAccountMapper;
import id.ridwan.minicbs.loan.account.LoanAccount;
import id.ridwan.minicbs.loan.account.LoanAccountRepository;
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
public class LoanAccountService {

    @Inject
    LoanAccountRepository repository;

    @Inject
    LoanAccountMapper mapper;

    public Uni<Map<String, Object>> findPagedAndFiltered(LocalDate fromDueDate, LocalDate toDueDate, Integer page, Integer size) {
        var query = repository.findPagedAndFiltered(fromDueDate, toDueDate, page, size);

        return query.list().onItem().transform((listOfLoanAccount) -> {
            List<LoanAccountDto> content = listOfLoanAccount.stream()
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

    public Uni<LoanAccountDto> findById(UUID id) {
        return this.findById(id, mapper::toDto);
    }

    @Transactional
    public Uni<LoanAccountDto> create(LoanAccountDto dto) {
        LoanAccount entity = mapper.toEntity(dto);
        return repository.persist(entity)
                .onItem().ifNotNull().transform(mapper::toDto)
                .onItem().ifNull().failWith(() -> new NotFoundException("LoanAccount not created"));
    }

    @Transactional
    public Uni<LoanAccountDto> update(UUID id, LoanAccountDto dto) {
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

    private <T> Uni<T> findById(UUID id, Function<LoanAccount, T> func) {
        return repository.findById(id)
                .onItem().ifNotNull().transform(func)
                .onItem().ifNull().failWith(() -> new NotFoundException("LoanAccount not found"));
    }
}
