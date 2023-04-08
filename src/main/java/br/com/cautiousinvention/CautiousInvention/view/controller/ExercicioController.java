package br.com.cautiousinvention.CautiousInvention.view.controller;


import br.com.cautiousinvention.CautiousInvention.service.ExercicioService;
import br.com.cautiousinvention.CautiousInvention.shared.ExercicioDTO;
import br.com.cautiousinvention.CautiousInvention.view.model.ExercicioRequest;
import br.com.cautiousinvention.CautiousInvention.view.model.ExercicioResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public ResponseEntity<List<ExercicioResponse>> obterExercicios() {
        List<ExercicioDTO> exercicios = exercicioService.obterTodosExercicios();
        List<ExercicioResponse> resposta = exercicios.stream().map(exercicioDto -> new ModelMapper().map(exercicioDto, ExercicioResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExercicioResponse> cadastrarExercicio(@RequestBody ExercicioRequest exercicioReq) {
        ExercicioDTO exercicioDto = new ModelMapper().map(exercicioReq, ExercicioDTO.class);
        exercicioDto = exercicioService.criarExercicio(exercicioDto);
        ExercicioResponse resposta = new ModelMapper().map(exercicioDto, ExercicioResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ExercicioResponse>> obterExercicioPorId(@PathVariable Integer id) {
        Optional<ExercicioDTO> dto = exercicioService.obterExercicioPorId(id);
        ExercicioResponse resposta = new ModelMapper().map(dto.get(), ExercicioResponse.class);
        return new ResponseEntity<>(Optional.of(resposta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarExercicio(@PathVariable Integer id) {
        exercicioService.deletarExercicio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponse> atualizarExercicio(@PathVariable Integer id, @RequestBody ExercicioRequest exercicioReq) {
        ExercicioDTO exercicioDto = new ModelMapper().map(exercicioReq, ExercicioDTO.class);
        exercicioDto = exercicioService.atualizarExercicio(id, exercicioDto);
        ExercicioResponse resposta = new ModelMapper().map(exercicioDto, ExercicioResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
