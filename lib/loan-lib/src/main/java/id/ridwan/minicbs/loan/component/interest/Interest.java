package id.ridwan.minicbs.loan.component.interest;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
public abstract class Interest extends BaseEntity {
    private BigDecimal rate;
}
