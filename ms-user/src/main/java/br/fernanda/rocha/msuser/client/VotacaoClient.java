package br.fernanda.rocha.msuser.client;

import br.fernanda.rocha.msuser.VotosData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ms-votacao", url = "http://localhost:8081")
public interface VotacaoClient {
    @GetMapping("/user-votos")
    List<String> listarVotos();

    @GetMapping("/user-votos")
    VotosData getVotos(@RequestBody VotosData votosData);

}


