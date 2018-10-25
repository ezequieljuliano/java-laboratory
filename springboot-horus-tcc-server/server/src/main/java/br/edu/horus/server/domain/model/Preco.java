package br.edu.horus.server.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "preco")
@SequenceGenerator(sequenceName = "seq_pre_id", name = "seq_pre_id", allocationSize = 1)
@NoArgsConstructor
@Data
public class Preco implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "seq_pre_id", strategy = GenerationType.SEQUENCE)
    @Column(name = "pre_id", nullable = false, updatable = false)
    private Long id;

    @NotNull(message = "Produto é uma informação obrigatória.")
    @ManyToOne
    @JoinColumn(
            name = "pre_pro_id", referencedColumnName = "pro_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pre_pro_id")
    )
    private Produto produto;

    @NotNull(message = "Fornecedor é uma informação obrigatória.")
    @ManyToOne
    @JoinColumn(
            name = "pre_for_id", referencedColumnName = "for_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pre_for_id")
    )
    private Fornecedor fornecedor;

    @NotNull(message = "Valor é uma informação obrigatória.")
    @Column(name = "pre_valor", precision = 15, scale = 2, nullable = false)
    private BigDecimal valor;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pre_criacao", nullable = false, updatable = false)
    private Date criacao;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pre_atualizacao", insertable = false)
    private Date atualizacao;

    @Version
    @Setter(AccessLevel.NONE)
    @Column(name = "pre_versao", nullable = false)
    private Long versao;

}
