package id.ridwan.minicbs.loan.account.disbursement;

import id.ridwan.minicbs.loan.component.disbursement.Disbursement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "loan_disbursement")
@Getter
@Setter
public class LoanDisbursement extends Disbursement {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
}
