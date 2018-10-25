package br.edu.horus.server.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "produto",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"pro_gtin"}, name = "uk_pro_gtin"
                )
        }
)
@SequenceGenerator(sequenceName = "seq_pro_id", name = "seq_pro_id", allocationSize = 1)
@NoArgsConstructor
@Data
public class Produto implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "seq_pro_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "pro_id", nullable = false, updatable = false)
    private Long id;

    @NotEmpty(message = "GTIN é uma informação obrigatória.")
    @Size(max = 14, message = "GTIN deve ter um tamanho máximo de 14 caracteres.")
    @Column(name = "pro_gtin", length = 14, nullable = false)
    private String gtin;

    @NotEmpty(message = "Nome é uma informação obrigatória.")
    @Size(max = 128, message = "Nome deve ter um tamanho máximo de 128 caracteres.")
    @Column(name = "pro_nome", length = 128, nullable = false)
    private String nome;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pro_criacao", nullable = false, updatable = false)
    private Date criacao;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pro_atualizacao", insertable = false)
    private Date atualizacao;

    @Version
    @Setter(AccessLevel.NONE)
    @Column(name = "pro_versao", nullable = false)
    private Long versao;

}
