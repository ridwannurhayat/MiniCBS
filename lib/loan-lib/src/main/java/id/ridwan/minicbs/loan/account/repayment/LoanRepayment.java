package id.ridwan.minicbs.loan.account.repayment;

import id.ridwan.minicbs.loan.component.repayment.Repayment;
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
public class LoanRepayment extends Repayment {

    @Id
    @NotNull
    private UUID id = UUID.randomUUID();
}
