package br.fernanda.rocha.msproposta.repository;

import br.fernanda.rocha.msproposta.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

}
