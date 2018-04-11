package com.ezequieljuliano.bookmark.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
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
@SequenceGenerator(sequenceName = "seq_cat_id", name = "category_identifier", allocationSize = 1)
@NoArgsConstructor
@EqualsAndHashCode
public class Category implements Serializable {

    @Id
    @GeneratedValue(generator = "category_identifier", strategy = GenerationType.SEQUENCE)
    @Column(name = "cat_id", nullable = false, updatable = false)
    @Getter
    private Long id;

    @Column(name = "cat_name", length = 60, nullable = false)
    @NotEmpty
    @Size(max = 60)
    @Getter
    @Setter
    private String name;

    @CreationTimestamp
    @Column(name = "cat_created", nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Getter
    private Date created;

    @UpdateTimestamp
    @Column(name = "cat_updated", insertable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Getter
    private Date updated;

    @Version
    @Column(name = "cat_version", nullable = false)
    @Getter
    private Long version;

    @Override
    public String toString() {
        return name.concat(" (").concat(id.toString()).concat(")");
    }

}
