package com.ezequieljuliano.bookmark.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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
@ToString
@Getter
@Setter
public class Category implements Serializable {

    @Id
    @GeneratedValue(generator = "category_identifier", strategy = GenerationType.SEQUENCE)
    @Column(name = "cat_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "cat_name", length = 60, nullable = false)
    @NotEmpty(message = "Nome é uma informação obrigatória.")
    @Size(max = 60, message = "Nome deve ter um tamanho máximo de 60 caracteres.")
    private String name;

    @CreationTimestamp
    @Column(name = "cat_created", nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date created;

    @UpdateTimestamp
    @Column(name = "cat_updated", insertable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date updated;

    @Version
    @Column(name = "cat_version", nullable = false)
    private Long version;
}
