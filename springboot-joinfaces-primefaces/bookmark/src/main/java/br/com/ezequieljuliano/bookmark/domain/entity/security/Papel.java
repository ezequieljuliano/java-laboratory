package br.com.ezequieljuliano.bookmark.domain.entity.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "papel")
@Data
@NoArgsConstructor
public class Papel implements Serializable {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "uuid", name = "pap_id", updatable = false)
    private UUID id;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "pap_nome")
    private String nome;

    @ManyToMany
    @JoinTable(name = "papel_permissao",
            joinColumns = {@JoinColumn(name = "ppe_pap_id", referencedColumnName = "pap_id")},
            inverseJoinColumns = {@JoinColumn(name = "ppe_per_id", referencedColumnName = "per_id")}
    )
    private List<Permissao> permissoes = new ArrayList<>();

}
