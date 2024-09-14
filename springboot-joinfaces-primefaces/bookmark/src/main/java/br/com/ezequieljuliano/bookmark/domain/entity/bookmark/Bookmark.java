package br.com.ezequieljuliano.bookmark.domain.entity.bookmark;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
