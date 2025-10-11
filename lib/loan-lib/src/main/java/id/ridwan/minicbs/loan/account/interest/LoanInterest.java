package id.ridwan.minicbs.loan.account.interest;

import id.ridwan.minicbs.loan.component.interest.Interest;
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
public class LoanInterest extends Interest {

    @Id
    @NotNull
    private UUID id = UUID.randomUUID();
}
