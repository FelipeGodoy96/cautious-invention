package br.com.cautiousinvention.CautiousInvention.view.controller;

import br.com.cautiousinvention.CautiousInvention.service.SerieService;
import br.com.cautiousinvention.CautiousInvention.service.SerieService;
import br.com.cautiousinvention.CautiousInvention.shared.SerieDTO;
import br.com.cautiousinvention.CautiousInvention.view.model.SerieRequest;
import br.com.cautiousinvention.CautiousInvention.view.model.SerieResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping
    public ResponseEntity<List<SerieResponse>> obterSeries() {
        List<SerieDTO> series = serieService.obterTodasSeries();
        List<SerieResponse> resposta = series.stream().map(serieDto -> new ModelMapper().map(serieDto, SerieResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SerieResponse> cadastrarSerie(@RequestBody SerieRequest serieReq) {
        SerieDTO serieDto = new ModelMapper().map(serieReq, SerieDTO.class);
        serieDto = serieService.criarSerie(serieDto);
        SerieResponse resposta = new ModelMapper().map(serieDto, SerieResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SerieResponse>> obterSeriePorId(@PathVariable Integer id) {
        Optional<SerieDTO> dto = serieService.obterSeriePorId(id);
        SerieResponse resposta = new ModelMapper().map(dto.get(), SerieResponse.class);
        return new ResponseEntity<>(Optional.of(resposta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarSerie(@PathVariable Integer id) {
        serieService.deletarSerie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SerieResponse> atualizarSerie(@PathVariable Integer id, @RequestBody SerieRequest serieReq) {
        SerieDTO serieDto = new ModelMapper().map(serieReq, SerieDTO.class);
        serieDto = serieService.atualizarSerie(id, serieDto);
        SerieResponse resposta = new ModelMapper().map(serieDto, SerieResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
