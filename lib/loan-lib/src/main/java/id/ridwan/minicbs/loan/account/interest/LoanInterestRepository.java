package id.ridwan.minicbs.loan.account.interest;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoanInterestRepository implements PanacheRepository<LoanInterest> {
}
