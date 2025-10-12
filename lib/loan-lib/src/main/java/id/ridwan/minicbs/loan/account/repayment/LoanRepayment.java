package id.ridwan.minicbs.loan.account.repayment;

import id.ridwan.minicbs.loan.component.repayment.Repayment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "loan_repayment")
@Getter
@Setter
public class LoanRepayment extends Repayment {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
}
