package br.com.fabiofnc.apileilao.repository;

import br.com.fabiofnc.apileilao.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
