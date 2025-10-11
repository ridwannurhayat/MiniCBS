package id.ridwan.minicbs.loan.account.disbursement;

import id.ridwan.minicbs.loan.component.disbursement.Disbursement;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class LoanDisbursement extends Disbursement {

    @Id
    @NotNull
    private UUID id = UUID.randomUUID();
}
