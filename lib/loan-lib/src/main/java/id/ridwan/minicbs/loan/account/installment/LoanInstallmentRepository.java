package id.ridwan.minicbs.loan.account.installment;

import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class LoanInstallmentRepository implements PanacheRepositoryBase<LoanInstallment, UUID> {

    public Uni<List<LoanInstallment>> findByLoanAccountId(UUID loanAccountId) {
        return list("loanAccount.id", loanAccountId);
    }

    public PanacheQuery<LoanInstallment> findPagedAndFiltered(
            UUID loanAccountId,
            LocalDate fromDate,
            LocalDate toDate,
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

        if (loanAccountId != null) {
            queryBuilder.append(" and loanAccount.id <= :loanAccountId");
            params.put("loanAccountId", loanAccountId);
        }

        PanacheQuery<LoanInstallment> query = find(queryBuilder.toString(), params);

        if (page != null && size != null) {
            query.page(page, size);
        }

        return query;
    }
}
