package id.ridwan.minicbs.loan.account.installment;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loan_installment")
@Getter
@Setter
public class LoanInstallment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "installment_date")
    private LocalDate installmentDate;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @NotNull
    @Column(name = "principal")
    private BigDecimal principal;

    @NotNull
    @Column(name = "interest")
    private BigDecimal interest;
}
