package br.fernanda.rocha.msuser.controller;

import br.fernanda.rocha.msuser.Funcionario;
import br.fernanda.rocha.msuser.VotosData;
import br.fernanda.rocha.msuser.client.VotacaoClient;
import br.fernanda.rocha.msuser.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final VotacaoClient votacaoClient;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioController(VotacaoClient votacaoClient) {
        this.votacaoClient = votacaoClient;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarFuncionario(@RequestBody Funcionario funcionario) {
        if (funcionarioRepository.existsByCpf(funcionario.getCpf())) {
            return ResponseEntity.badRequest().body("CPF já está cadastrado.");
        }


        Funcionario novoFuncionario = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok(novoFuncionario);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/verificar")
    public boolean verificarFuncionarioCadastrado(@RequestParam("cpf") String cpf) {

        return funcionarioRepository.existsByCpf(cpf);
    }

    @GetMapping("/teste")
    public VotosData teste(@RequestParam("cpf") String cpf) {
        VotosData votosData = new VotosData();
        votosData.setCpf(cpf);
        return votacaoClient.getVotos(votosData);
    }
}
