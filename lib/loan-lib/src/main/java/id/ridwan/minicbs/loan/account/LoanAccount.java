package id.ridwan.minicbs.loan.account;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class LoanAccount extends BaseEntity {

    @Id
    @NotNull
    private UUID id = UUID.randomUUID();
    private LocalDate dueDate;
}
