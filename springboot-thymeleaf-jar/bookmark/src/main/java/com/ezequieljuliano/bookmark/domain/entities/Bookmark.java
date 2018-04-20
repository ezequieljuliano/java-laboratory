package com.ezequieljuliano.bookmark.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bookmark")
@SequenceGenerator(sequenceName = "seq_boo_id", name = "bookmark_identifier", allocationSize = 1)
@NoArgsConstructor
@Data
public class Bookmark implements Serializable {

    @Id
    @GeneratedValue(generator = "bookmark_identifier", strategy = GenerationType.SEQUENCE)
    @Column(name = "boo_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "boo_description", length = 255, nullable = false)
    @NotEmpty(message = "Descrição é uma informação obrigatória.")
    @Size(max = 255, message = "Descrição deve ter um tamanho máximo de 255 caracteres.")
    private String description;

    @Column(name = "boo_link", length = 255, nullable = false)
    @NotEmpty(message = "Link é uma informação obrigatória.")
    @Size(max = 255, message = "Link deve ter um tamanho máximo de 255 caracteres.")
    private String link;

    @ManyToOne
    @JoinColumn(name = "boo_cat_id", referencedColumnName = "cat_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_boo_cat_id")
    )
    @NotNull(message = "Categoria é uma informação obrigatória.")
    private Category category;

    @CreationTimestamp
    @Column(name = "boo_created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date created;

    @UpdateTimestamp
    @Column(name = "boo_updated", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date updated;

    @Version
    @Column(name = "boo_version", nullable = false)
    private Long version;
}
