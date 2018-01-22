package com.ezequieljuliano.bookmark.entities;

import com.ezequieljuliano.bookmark.entities.enums.UserStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "user_identifier", strategy = GenerationType.SEQUENCE)
    @Column(name = "use_id", nullable = false, updatable = false)
    @Getter
    private Long id;

    @Column(name = "use_username", length = 128, nullable = false)
    @NotNull
    @Size(max = 128)
    @Getter
    @Setter
    private String username;

    @Column(name = "use_password", length = 60, nullable = false)
    @NotNull
    @Size(max = 60)
    @Getter
    private String password;

    @Column(name = "use_name", length = 60, nullable = false)
    @NotNull
    @Size(max = 60)
    @Getter
    @Setter
    private String name;

    @Column(name = "use_email", length = 128, nullable = false)
    @NotNull
    @Size(max = 128)
    @Email
    @Getter
    @Setter
    private String email;

    @Column(name = "use_status", length = 30, nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private UserStatus status;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "security_user_role",
            joinColumns = {
                @JoinColumn(name = "usr_use_id", referencedColumnName = "use_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usr_use_id"))
            },
            inverseJoinColumns = {
                @JoinColumn(name = "usr_rol_id", referencedColumnName = "rol_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usr_rol_id"))
            }
    )
    @Setter
    private List<Role> roles;

    @CreationTimestamp
    @Column(name = "use_created", nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Getter
    private Date created;

    @UpdateTimestamp
    @Column(name = "use_updated", insertable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Getter
    private Date updated;

    @Version
    @Column(name = "use_version", nullable = false)
    @Getter
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
