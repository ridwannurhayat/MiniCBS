package id.ridwan.minicbs.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @NotNull
    @Column(name = "create_time")
    @CreationTimestamp
    private LocalDateTime createTime;

    @NotNull
    @Column(name = "update_time")
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @Setter
    @Column(name = "active", nullable = false)
    private Boolean active = true;

}
