package br.com.ezequieljuliano.blog.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "post")
@SequenceGenerator(sequenceName = "seq_pos_id", name = "post_identifier", allocationSize = 1)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "post_identifier", strategy = GenerationType.SEQUENCE)
    @Column(name = "pos_id", nullable = false, updatable = false)
    @Getter
    private Long id;

    @Column(name = "pos_autor", length = 60, nullable = false)
    @Size(max = 60)
    @Getter
    @Setter
    @NotBlank(message = "Autor é uma informação obrigatória.")
    private String autor;

    @Column(name = "pos_titulo", length = 120, nullable = false)
    @Size(max = 120)
    @Getter
    @Setter
    @NotBlank(message = "Título é uma informação obrigatória.")
    private String titulo;

    @Column(name = "pos_texto", length = 2000, nullable = false)
    @Size(max = 2000)
    @Getter
    @Setter
    @NotBlank(message = "Texto é uma informação obrigatória.")
    private String texto;

    @Column(name = "pos_data", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Getter
    @Setter
    @NotNull(message = "Data é uma informação obrigatória.")
    private Date data;
}
