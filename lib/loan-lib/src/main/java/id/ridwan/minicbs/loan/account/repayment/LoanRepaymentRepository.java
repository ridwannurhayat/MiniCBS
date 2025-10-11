package id.ridwan.minicbs.loan.account.repayment;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoanRepaymentRepository implements PanacheRepository<LoanRepayment> {
}
