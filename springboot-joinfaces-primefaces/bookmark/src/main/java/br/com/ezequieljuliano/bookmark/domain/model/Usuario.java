package br.com.ezequieljuliano.bookmark.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "uuid", name = "usu_id", updatable = false)
    private UUID id;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "usu_username")
    private String username;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "usu_password")
    private String password;

    @NotEmpty
    @Size(max = 255)
    @Column(name = "usu_nome")
    private String nome;

    @Email
    @NotEmpty
    @Size(max = 255)
    @Column(name = "usu_email")
    private String email;

    @NotNull
    @Column(name = "usu_ativo")
    private Boolean ativo;

    @NotNull
    @Column(name = "usu_administrador")
    private Boolean administrador;

    @ManyToMany
    @JoinTable(name = "usuario_papel",
            joinColumns = {@JoinColumn(name = "usp_usu_id", referencedColumnName = "usu_id")},
            inverseJoinColumns = {@JoinColumn(name = "usp_pap_id", referencedColumnName = "pap_id")}
    )
    private List<Papel> papeis = new ArrayList<>();

    public String getNomesPapeisAtribuidos() {
        return papeis.stream().map(p -> String.valueOf(p.getNome())).collect(Collectors.joining(", "));
    }

}
