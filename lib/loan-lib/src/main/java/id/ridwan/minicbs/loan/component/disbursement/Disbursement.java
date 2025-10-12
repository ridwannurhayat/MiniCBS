package id.ridwan.minicbs.loan.component.disbursement;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class Disbursement extends BaseEntity {
    @NotNull
    @Column(name = "disbursement_date", nullable = false)
    private LocalDate disbursementDate;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;
}
