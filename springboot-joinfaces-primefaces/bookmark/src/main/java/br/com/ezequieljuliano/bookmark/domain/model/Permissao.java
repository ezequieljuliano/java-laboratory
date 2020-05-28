package br.com.ezequieljuliano.bookmark.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "permissao")
@Data
@NoArgsConstructor
public class Permissao implements Serializable {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "uuid", name = "per_id", updatable = false)
    private UUID id;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "per_recurso")
    private String recurso;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "per_acao")
    private String acao;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "per_descricao")
    private String descricao;

}
