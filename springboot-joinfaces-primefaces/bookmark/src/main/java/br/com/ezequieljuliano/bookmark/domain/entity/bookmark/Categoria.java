package br.com.ezequieljuliano.bookmark.domain.entity.bookmark;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
public class Categoria implements Serializable {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "uuid", name = "cat_id", updatable = false)
    private UUID id;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "cat_nome")
    private String nome;

}
