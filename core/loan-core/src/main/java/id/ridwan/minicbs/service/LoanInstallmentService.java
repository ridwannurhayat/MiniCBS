package id.ridwan.minicbs.service;

import id.ridwan.minicbs.domain.installment.LoanInstallmentDto;
import id.ridwan.minicbs.domain.installment.LoanInstallmentMapper;
import id.ridwan.minicbs.loan.account.installment.LoanInstallment;
import id.ridwan.minicbs.loan.account.installment.LoanInstallmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class LoanInstallmentService {

    @Inject
    LoanInstallmentRepository repository;

    @Inject
    LoanInstallmentMapper mapper;

    public List<LoanInstallmentDto> findAll() {
        return repository.listAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public Map<String, Object> findPagedAndFiltered(
            LocalDate fromDate,
            LocalDate toDate,
            BigDecimal minAmount,
            BigDecimal maxAmount,
            Integer page,
            Integer size
    ) {
        var query = repository.findPagedAndFiltered(fromDate, toDate, minAmount, maxAmount, page, size);

        List<LoanInstallmentDto> content = query.list().stream()
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

    public LoanInstallmentDto findById(UUID id) {
        LoanInstallment entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("LoanInstallment not found");
        }
        return mapper.toDto(entity);
    }

    @Transactional
    public LoanInstallmentDto create(LoanInstallmentDto dto) {
        LoanInstallment entity = mapper.toEntity(dto);
        repository.persist(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public LoanInstallmentDto update(UUID id, LoanInstallmentDto dto) {
        LoanInstallment entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("LoanInstallment not found");
        }

        mapper.updateEntityFromDto(dto, entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public void delete(UUID id) {
        LoanInstallment entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("LoanAccount not found");
        }
        entity.setActive(false);
    }
}
