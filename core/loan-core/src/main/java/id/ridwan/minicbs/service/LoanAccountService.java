package id.ridwan.minicbs.service;

import id.ridwan.minicbs.domain.account.LoanAccountDto;
import id.ridwan.minicbs.domain.account.LoanAccountMapper;
import id.ridwan.minicbs.loan.account.LoanAccount;
import id.ridwan.minicbs.loan.account.LoanAccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class LoanAccountService {

    @Inject
    LoanAccountRepository repository;

    @Inject
    LoanAccountMapper mapper;

    public List<LoanAccountDto> findAll() {
        return repository.listAll().stream()
                .map(mapper::toDto)
                .toList();
    }
    public Map<String, Object> findPagedAndFiltered(LocalDate fromDueDate, LocalDate toDueDate, Integer page, Integer size) {
        var query = repository.findPagedAndFiltered(fromDueDate, toDueDate, page, size);

        List<LoanAccountDto> content = query.list().stream()
                .map(mapper::toDto)
                .toList();

        Map<String, Object> result = new HashMap<>();
        result.put("content", content);
        result.put("page", page == null ? 0 : page);
        result.put("size", size == null ? content.size() : size);
        result.put("totalElements", query.count());
        result.put("totalPages", query.pageCount());

        return result;
    }

    public LoanAccountDto findById(UUID id) {
        LoanAccount entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("LoanAccount not found");
        }
        return mapper.toDto(entity);
    }

    @Transactional
    public LoanAccountDto create(LoanAccountDto dto) {
        LoanAccount entity = mapper.toEntity(dto);
        repository.persist(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public LoanAccountDto update(UUID id, LoanAccountDto dto) {
        LoanAccount entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("LoanAccount not found");
        }

        mapper.updateEntityFromDto(dto, entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public void delete(UUID id) {
        LoanAccount entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("LoanAccount not found");
        }
        entity.setActive(false);
    }
}
