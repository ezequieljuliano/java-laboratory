package com.ezequieljuliano.bookmark.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "security_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"use_username"}, name = "uk_use_username"
                )
                ,
                @UniqueConstraint(
                        columnNames = {"use_email"}, name = "uk_use_email"
                )
        }
)
@SequenceGenerator(sequenceName = "seq_use_id", name = "user_identifier", allocationSize = 1)
@NoArgsConstructor
@Data
public class User implements Serializable {

    public enum Status implements Serializable {

        ACTIVE("Ativo"),
        INACTIVE("Inativo"),
        BLOCKED("Bloqueado");

        private final String description;

        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

    }

    @Id
    @GeneratedValue(generator = "user_identifier", strategy = GenerationType.SEQUENCE)
    @Column(name = "use_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "use_username", length = 128, nullable = false)
    @NotEmpty(message = "Usuário é uma informação obrigatória.")
    @Size(max = 128, message = "Usuário deve ter um tamanho máximo de 128 caracteres.")
    private String username;

    @Column(name = "use_password", length = 60, nullable = false)
    @NotEmpty(message = "Senha é uma informação obrigatória.")
    @Size(max = 60, message = "Senha deve ter um tamanho máximo de 60 caracteres.")
    private String password;

    @Column(name = "use_name", length = 60, nullable = false)
    @NotEmpty(message = "Nome é uma informação obrigatória.")
    @Size(max = 60, message = "Nome deve ter um tamanho máximo de 60 caracteres.")
    private String name;

    @Column(name = "use_email", length = 128, nullable = false)
    @NotEmpty(message = "E-mail uma informação obrigatória.")
    @Size(max = 128, message = "E-mail deve ter um tamanho máximo de 128 caracteres.")
    @Email(message = "E-mail informado é inválido.")
    private String email;

    @Column(name = "use_status", length = 30, nullable = false)
    @NotNull(message = "Situação é uma informação obrigatória.")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "security_user_role",
            joinColumns = {
                    @JoinColumn(name = "usr_use_id", referencedColumnName = "use_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usr_use_id"))
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "usr_rol_id", referencedColumnName = "rol_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usr_rol_id"))
            }
    )
    private List<Role> roles;

    @CreationTimestamp
    @Column(name = "use_created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date created;

    @UpdateTimestamp
    @Column(name = "use_updated", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date updated;

    @Version
    @Column(name = "use_version", nullable = false)
    private Long version;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public String getRolesDescriptions() {
        String descriptions = "";
        for (int i = 0; i < getRoles().size(); i++) {
            descriptions = descriptions.concat(getRoles().get(i).getDescription());
            if (i < (getRoles().size() - 1)) {
                descriptions = descriptions.concat(", ");
            }
        }
        return descriptions;
    }

    public boolean containsRole(Role role) {
        for (Role currentRole : getRoles()) {
            if (currentRole.getName().equals(role.getName())) {
                return true;
            }
        }
        return false;
    }
}
