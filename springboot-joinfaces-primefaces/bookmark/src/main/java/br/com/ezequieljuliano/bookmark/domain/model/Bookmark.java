package br.com.ezequieljuliano.bookmark.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "bookmark")
@Data
@NoArgsConstructor
public class Bookmark implements Serializable {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "uuid", name = "boo_id", updatable = false)
    private UUID id;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "boo_descricao")
    private String descricao;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "boo_link")
    private String link;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "boo_cat_id", referencedColumnName = "cat_id")
    private Categoria categoria;

}
