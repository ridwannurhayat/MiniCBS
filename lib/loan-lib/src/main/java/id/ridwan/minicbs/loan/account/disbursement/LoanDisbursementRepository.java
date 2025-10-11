package id.ridwan.minicbs.loan.account.disbursement;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoanDisbursementRepository implements PanacheRepository<LoanDisbursement> {
}
