package br.fernanda.rocha.msuser.repository;
import br.fernanda.rocha.msuser.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsByCpf(String cpf);
}

