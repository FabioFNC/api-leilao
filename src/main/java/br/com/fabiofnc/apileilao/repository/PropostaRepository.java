package br.com.fabiofnc.apileilao.repository;

import br.com.fabiofnc.apileilao.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
