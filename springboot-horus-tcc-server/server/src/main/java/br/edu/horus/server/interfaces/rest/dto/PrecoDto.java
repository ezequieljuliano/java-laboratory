package br.edu.horus.server.interfaces.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PrecoDto implements Serializable {

    @Valid
    @NotNull(message = "Produto é uma informação obrigatória.")
    private ProdutoDto produto;

    @Valid
    @NotNull(message = "Fornecedor é uma informação obrigatória.")
    private FornecedorDto fornecedor;

    @NotNull(message = "Preço é uma informação obrigatória.")
    private BigDecimal preco;

}
