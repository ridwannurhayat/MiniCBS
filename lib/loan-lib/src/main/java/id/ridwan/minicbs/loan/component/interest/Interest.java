package id.ridwan.minicbs.loan.component.interest;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
public abstract class Interest extends BaseEntity {
    @NotNull
    @Column(name = "rate", nullable = false)
    private BigDecimal rate;
}
