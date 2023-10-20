package br.fernanda.rocha.msuser.service;

import br.fernanda.rocha.msuser.Funcionario;
import br.fernanda.rocha.msuser.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }


}

