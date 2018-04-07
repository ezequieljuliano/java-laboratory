package com.ezequieljuliano.bookmark.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "security_role",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"rol_name"}, name = "uk_rol_name"
                )
        }
)
@SequenceGenerator(sequenceName = "seq_rol_id", name = "role_identifier", allocationSize = 1)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Role implements Serializable {

    @Id
    @GeneratedValue(generator = "role_identifier", strategy = GenerationType.SEQUENCE)
    @Column(name = "rol_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "rol_name", length = 30, nullable = false)
    @NotEmpty(message = "Nome é uma informação obrigatória.")
    @Size(max = 30, message = "Nome deve ter um tamanho máximo de 30 caracteres.")
    private String name;

    @Column(name = "rol_description", length = 60, nullable = false)
    @NotEmpty(message = "Descrição é uma informação obrigatória.")
    @Size(max = 60, message = "Descrição deve ter um tamanho máximo de 60 caracteres.")
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @CreationTimestamp
    @Column(name = "rol_created", nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date created;

    @UpdateTimestamp
    @Column(name = "rol_updated", insertable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date updated;

    @Version
    @Column(name = "rol_version", nullable = false)
    private Long version;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }
}
