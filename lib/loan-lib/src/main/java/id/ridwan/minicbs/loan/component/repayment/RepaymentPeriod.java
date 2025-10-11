package id.ridwan.minicbs.loan.component.repayment;

import lombok.Getter;

@Getter
public enum RepaymentPeriod {
    MONTHLY("M"),
    ANNUALLY("A");

    private final String id;

    RepaymentPeriod(String id) {
        this.id = id;
    }

    public static RepaymentPeriod of(String id) {
        for (RepaymentPeriod e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return null;
    }
}
