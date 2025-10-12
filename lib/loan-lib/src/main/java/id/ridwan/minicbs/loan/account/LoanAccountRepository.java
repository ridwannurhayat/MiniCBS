package id.ridwan.minicbs.loan.account;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class LoanAccountRepository implements PanacheRepository<LoanAccount> {
    public LoanAccount findById(UUID id) {
        return find("id", id).firstResult();
    }

    public PanacheQuery<LoanAccount> findPagedAndFiltered(
            LocalDate fromDueDate,
            LocalDate toDueDate,
            Integer page,
            Integer size
    ) {
        StringBuilder queryBuilder = new StringBuilder("1=1");
        Map<String, Object> params = new HashMap<>();

        if (fromDueDate != null) {
            queryBuilder.append(" and dueDate >= :fromDueDate");
            params.put("fromDueDate", fromDueDate);
        }

        if (toDueDate != null) {
            queryBuilder.append(" and dueDate <= :toDueDate");
            params.put("toDueDate", toDueDate);
        }

        PanacheQuery<LoanAccount> query = find(queryBuilder.toString(), params);

        // Apply pagination
        if (page != null && size != null) {
            query.page(page, size);
        }

        return query;
    }
}
