package id.ridwan.minicbs.loan.component.repayment;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Repayment extends BaseEntity {

    public enum RepaymentMethod {
        EQUAL_INSTALLMENT
    }

    @NotNull
    @Column(name = "repayment_method", nullable = false)
    private RepaymentMethod repaymentMethod;

    @NotNull
    @Column(name = "repayment_day", nullable = false)
    private Integer repaymentDay;

    @NotNull
    @Column(name = "repayment_period", nullable = false)
    private RepaymentPeriod repaymentPeriod;
}
