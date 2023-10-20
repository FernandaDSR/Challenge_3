package br.fernanda.rocha.msproposta.repository;

import br.fernanda.rocha.msproposta.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findByCpfAndPropostaId(String cpf, Long propostaId);

    boolean existsByCpfAndPropostaId(String cpf, Long propostaId);
}


