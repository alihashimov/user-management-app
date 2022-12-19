package az.lesson.user.management.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Builder
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "privileges")
@EntityListeners(AuditingEntityListener.class)
public class Privilege {

    @Id
    @SequenceGenerator(name = "privileges_seq_gen", sequenceName = "privileges_seq_gen")
    @GeneratedValue(strategy = SEQUENCE, generator = "privileges_seq_gen")
    @Column(updatable = false, nullable = false, insertable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String type;

    @ToString.Exclude
    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new LinkedHashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(nullable = false, updatable = false, insertable = false, length = 30)
    private String createdBy;

    @LastModifiedDate
    @Column(nullable = false, insertable = false)
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    @Column(nullable = false, insertable = false, length = 30)
    private String lastModifiedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Privilege privilege = (Privilege) o;
        return id != null && Objects.equals(id, privilege.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
