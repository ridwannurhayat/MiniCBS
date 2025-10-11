package id.ridwan.minicbs.loan.component.disbursement;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class Disbursement extends BaseEntity {
    private LocalDate date;
    private BigDecimal amount;
}
