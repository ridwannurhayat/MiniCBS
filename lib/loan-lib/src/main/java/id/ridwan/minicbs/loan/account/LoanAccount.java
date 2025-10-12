package id.ridwan.minicbs.loan.account;

import id.ridwan.minicbs.common.entity.BaseEntity;
import id.ridwan.minicbs.loan.account.disbursement.LoanDisbursement;
import id.ridwan.minicbs.loan.account.installment.LoanInstallment;
import id.ridwan.minicbs.loan.account.interest.LoanInterest;
import id.ridwan.minicbs.loan.account.repayment.LoanRepayment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "loan_account")
@Getter
@Setter
public class LoanAccount extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "loan_account_id") // creates FK in child tables
    private List<LoanDisbursement> disbursements = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "interest_id")
    private LoanInterest interest;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "repayment_id")
    private LoanRepayment repayment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "loan_account_id")
    private List<LoanInstallment> installments = new ArrayList<>();
}
