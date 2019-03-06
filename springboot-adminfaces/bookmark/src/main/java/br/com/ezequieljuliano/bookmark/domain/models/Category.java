package br.com.ezequieljuliano.bookmark.domain.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "category")
@SequenceGenerator(sequenceName = "seq_cat_id", name = "seq_cat_id", allocationSize = 1)
@NoArgsConstructor
@Data
public class Category implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "seq_cat_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "cat_id", nullable = false, updatable = false)
    private Long id;

    @NotEmpty
    @Size(max = 60)
    @Column(name = "cat_name", length = 60, nullable = false)
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    @Column(name = "cat_created", nullable = false, updatable = false)
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    @Column(name = "cat_updated", insertable = false)
    private Date updated;

    @Version
    @Setter(AccessLevel.NONE)
    @Column(name = "cat_version", nullable = false)
    private Long version;

}
