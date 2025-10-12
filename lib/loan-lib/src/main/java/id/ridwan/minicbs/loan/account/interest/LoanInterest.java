package id.ridwan.minicbs.loan.account.interest;

import id.ridwan.minicbs.loan.component.interest.Interest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "loan_interest")
@Getter
@Setter
public class LoanInterest extends Interest {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
}
