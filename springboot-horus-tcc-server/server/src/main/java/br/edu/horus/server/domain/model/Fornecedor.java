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
@Table(name = "fornecedor",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"for_documento"}, name = "uk_for_documento"
                )
        }
)
@SequenceGenerator(sequenceName = "seq_for_id", name = "seq_for_id", allocationSize = 1)
@NoArgsConstructor
@Data
public class Fornecedor implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "seq_for_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "for_id", nullable = false, updatable = false)
    private Long id;

    @NotEmpty(message = "Documento (CPF/CNPJ) é uma informação obrigatória.")
    @Size(max = 14, message = "Documento (CPF/CNPJ) deve ter um tamanho máximo de 14 caracteres.")
    @Column(name = "for_documento", length = 14, nullable = false)
    private String documento;

    @NotEmpty(message = "Nome é uma informação obrigatória.")
    @Size(max = 128, message = "Nome deve ter um tamanho máximo de 128 caracteres.")
    @Column(name = "for_nome", length = 128, nullable = false)
    private String nome;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "for_criacao", nullable = false, updatable = false)
    private Date criacao;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "for_atualizacao", insertable = false)
    private Date atualizacao;

    @Version
    @Setter(AccessLevel.NONE)
    @Column(name = "for_versao", nullable = false)
    private Long versao;

}
