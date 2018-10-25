package br.edu.horus.server.interfaces.rest.resource;

import br.edu.horus.server.domain.model.Fornecedor;
import br.edu.horus.server.domain.model.Preco;
import br.edu.horus.server.domain.model.Produto;
import br.edu.horus.server.domain.repository.FornecedorRepository;
import br.edu.horus.server.domain.repository.PrecoRepository;
import br.edu.horus.server.domain.repository.ProdutoRepository;
import br.edu.horus.server.interfaces.rest.dto.FornecedorDto;
import br.edu.horus.server.interfaces.rest.dto.PrecoDto;
import br.edu.horus.server.interfaces.rest.dto.ProdutoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/precos")
public class PrecoController {

    private final PrecoRepository precoRepository;
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public PrecoController(PrecoRepository precoRepository,
                           ProdutoRepository produtoRepository,
                           FornecedorRepository fornecedorRepository) {
        this.precoRepository = precoRepository;
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    private void inserirOuAtualizarProduto(ProdutoDto produtoDto) {
        Optional<Produto> produtoOptional = produtoRepository.findByGtin(produtoDto.getGtin());
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.setNome(produtoDto.getNome());
            produtoRepository.save(produto);
        } else {
            Produto produto = new Produto();
            produto.setGtin(produtoDto.getGtin());
            produto.setNome(produtoDto.getNome());
            produtoRepository.save(produto);
        }
    }

    private void inserirOuAtualizarFornecedor(FornecedorDto fornecedorDto) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findByDocumento(fornecedorDto.getDocumento());
        if (fornecedorOptional.isPresent()) {
            Fornecedor fornecedor = fornecedorOptional.get();
            fornecedor.setNome(fornecedorDto.getNome());
            fornecedorRepository.save(fornecedor);
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setDocumento(fornecedorDto.getDocumento());
            fornecedor.setNome(fornecedorDto.getNome());
            fornecedorRepository.save(fornecedor);
        }
    }

    private PrecoDto precoDtoFromPreco(Preco preco) {
        PrecoDto result = new PrecoDto();
        result.setFornecedor(new FornecedorDto(preco.getFornecedor().getDocumento(), preco.getFornecedor().getNome()));
        result.setProduto(new ProdutoDto(preco.getProduto().getGtin(), preco.getProduto().getNome()));
        result.setPreco(preco.getValor());
        return result;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid PrecoDto precoDto) {
        inserirOuAtualizarProduto(precoDto.getProduto());
        inserirOuAtualizarFornecedor(precoDto.getFornecedor());

        Optional<Preco> precoOptional = precoRepository.findByProdutoGtinAndFornecedorDocumento(
                precoDto.getProduto().getGtin(),
                precoDto.getFornecedor().getDocumento()
        );

        if (precoOptional.isPresent()) {
            Preco preco = precoOptional.get();
            preco.setValor(precoDto.getPreco());
            precoRepository.save(preco);
        } else {
            Preco preco = new Preco();
            preco.setProduto(produtoRepository.findByGtin(precoDto.getProduto().getGtin()).get());
            preco.setFornecedor(fornecedorRepository.findByDocumento(precoDto.getFornecedor().getDocumento()).get());
            preco.setValor(precoDto.getPreco());
            precoRepository.save(preco);
        }
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PrecoDto> pesquisar() {
        List<PrecoDto> result = new ArrayList<>();

        List<Preco> precos = precoRepository.findAll();
        for (Preco preco : precos) {
            result.add(precoDtoFromPreco(preco));
        }

        return result;
    }

    @GetMapping("/pesquisar-por-produto")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PrecoDto> pesquisarPorProduto(@RequestParam("nome") String nome) {
        List<PrecoDto> result = new ArrayList<>();

        List<Preco> precos = precoRepository.findByProdutoNomeContainingIgnoreCase(nome);
        for (Preco preco : precos) {
            result.add(precoDtoFromPreco(preco));
        }

        return result;
    }

    @GetMapping("/pesquisar-por-fornecedor")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PrecoDto> pesquisarPorFornecedor(@RequestParam("nome") String nome) {
        List<PrecoDto> result = new ArrayList<>();

        List<Preco> precos = precoRepository.findByFornecedorNomeContainingIgnoreCase(nome);
        for (Preco preco : precos) {
            result.add(precoDtoFromPreco(preco));
        }

        return result;
    }

}
