package br.com.cautiousinvention.CautiousInvention.view.controller;

import br.com.cautiousinvention.CautiousInvention.service.TreinoService;
import br.com.cautiousinvention.CautiousInvention.service.TreinoService;
import br.com.cautiousinvention.CautiousInvention.shared.TreinoDTO;
import br.com.cautiousinvention.CautiousInvention.view.model.TreinoRequest;
import br.com.cautiousinvention.CautiousInvention.view.model.TreinoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @GetMapping
    public ResponseEntity<List<TreinoResponse>> obterTreinos() {
        List<TreinoDTO> treinos = treinoService.obterTodosTreinos();
        List<TreinoResponse> resposta = treinos.stream().map(treinoDto -> new ModelMapper().map(treinoDto, TreinoResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TreinoResponse> cadastrarTreino(@RequestBody TreinoRequest treinoReq) {
        TreinoDTO treinoDto = new ModelMapper().map(treinoReq, TreinoDTO.class);
        treinoDto = treinoService.criarTreino(treinoDto);
        TreinoResponse resposta = new ModelMapper().map(treinoDto, TreinoResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TreinoResponse>> obterTreinoPorId(@PathVariable Integer id) {
        Optional<TreinoDTO> dto = treinoService.obterTreinoPorId(id);
        TreinoResponse resposta = new ModelMapper().map(dto.get(), TreinoResponse.class);
        return new ResponseEntity<>(Optional.of(resposta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTreino(@PathVariable Integer id) {
        treinoService.deletarTreino(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoResponse> atualizarTreino(@PathVariable Integer id, @RequestBody TreinoRequest treinoReq) {
        TreinoDTO treinoDto = new ModelMapper().map(treinoReq, TreinoDTO.class);
        treinoDto = treinoService.atualizarTreino(id, treinoDto);
        TreinoResponse resposta = new ModelMapper().map(treinoDto, TreinoResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
