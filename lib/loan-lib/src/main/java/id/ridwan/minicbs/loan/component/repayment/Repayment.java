package id.ridwan.minicbs.loan.component.repayment;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Repayment extends BaseEntity {

    public enum RepaymentMethod {
        EQUAL_INSTALLMENT
    }

    private RepaymentMethod repaymentMethod;
    private Integer repaymentDate;
    private RepaymentPeriod repaymentPeriod;
}
