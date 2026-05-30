package id.ridwan.minicbs.accounting;

import id.ridwan.minicbs.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "journal_entry")
@Getter
@Setter
public class JournalEntry extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "reference_type", nullable = false)
    private String referenceType;

    @NotNull
    @Column(name = "reference_id", nullable = false)
    private UUID referenceId;

    // ✅ Unidirectional OneToMany
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "journal_entry_id") // FK lives in JournalLine table
    private List<JournalLine> lines = new ArrayList<>();

    public void addLine(JournalLine line) {
        this.lines.add(line);
    }
}
