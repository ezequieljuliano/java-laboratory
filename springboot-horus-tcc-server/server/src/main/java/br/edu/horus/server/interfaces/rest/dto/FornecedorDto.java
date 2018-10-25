package br.edu.horus.server.interfaces.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class FornecedorDto implements Serializable {

    @NotEmpty(message = "Documento (CPF/CNPJ) é uma informação obrigatória.")
    @Size(max = 14, message = "Documento (CPF/CNPJ) deve ter um tamanho máximo de 14 caracteres.")
    private String documento;

    @NotEmpty(message = "Nome é uma informação obrigatória.")
    @Size(max = 128, message = "Nome deve ter um tamanho máximo de 128 caracteres.")
    private String nome;

    public FornecedorDto(String documento, String nome) {
        this.documento = documento;
        this.nome = nome;
    }

}
