package br.fernanda.rocha.msproposta.cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ComponentScan
@FeignClient(name = "ms-funcionarios", url ="http://localhost:8080" )
public interface FuncionariosClient {
    @GetMapping("/funcionarios/verificar")
    boolean verificarFuncionarioCadastrado(@RequestParam("cpf") String cpf);
}



