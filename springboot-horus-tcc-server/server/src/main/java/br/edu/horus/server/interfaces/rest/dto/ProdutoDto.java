package br.edu.horus.server.interfaces.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ProdutoDto implements Serializable {

    @NotEmpty(message = "GTIN é uma informação obrigatória.")
    @Size(max = 14, message = "GTIN deve ter um tamanho máximo de 14 caracteres.")
    private String gtin;

    @NotEmpty(message = "Nome é uma informação obrigatória.")
    @Size(max = 128, message = "Nome deve ter um tamanho máximo de 128 caracteres.")
    private String nome;

    public ProdutoDto(String gtin, String nome) {
        this.gtin = gtin;
        this.nome = nome;
    }

}
