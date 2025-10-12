package id.ridwan.minicbs.loan.account.installment;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class LoanInstallmentRepository implements PanacheRepository<LoanInstallment> {

    public List<LoanInstallment> findByLoanAccountId(UUID loanAccountId) {
        return list("loanAccount.id", loanAccountId);
    }

    public LoanInstallment findById(UUID id) {
        return find("id", id).firstResult();
    }

    public PanacheQuery<LoanInstallment> findPagedAndFiltered(
            LocalDate fromDate,
            LocalDate toDate,
            BigDecimal minAmount,
            BigDecimal maxAmount,
            Integer page,
            Integer size
    ) {
        StringBuilder queryBuilder = new StringBuilder("1=1");
        Map<String, Object> params = new HashMap<>();

        if (fromDate != null) {
            queryBuilder.append(" and installmentDate >= :fromDate");
            params.put("fromDate", fromDate);
        }

        if (toDate != null) {
            queryBuilder.append(" and installmentDate <= :toDate");
            params.put("toDate", toDate);
        }

        if (minAmount != null) {
            queryBuilder.append(" and amount >= :minAmount");
            params.put("minAmount", minAmount);
        }

        if (maxAmount != null) {
            queryBuilder.append(" and amount <= :maxAmount");
            params.put("maxAmount", maxAmount);
        }

        PanacheQuery<LoanInstallment> query = find(queryBuilder.toString(), params);

        if (page != null && size != null) {
            query.page(page, size);
        }

        return query;
    }
}
