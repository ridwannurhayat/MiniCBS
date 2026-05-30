package id.ridwan.minicbs.loan.account.repayment;

//import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class LoanRepaymentRepository implements PanacheRepositoryBase<LoanRepayment, UUID> {
}
