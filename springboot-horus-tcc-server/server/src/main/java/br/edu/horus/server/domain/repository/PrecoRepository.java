package br.edu.horus.server.domain.repository;

import br.edu.horus.server.domain.model.Preco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, Long> {

    Optional<Preco> findByProdutoGtinAndFornecedorDocumento(String gtin, String documento);

    List<Preco> findByFornecedorNomeContainingIgnoreCase(String nome);

    List<Preco> findByProdutoNomeContainingIgnoreCase(String nome);

}
