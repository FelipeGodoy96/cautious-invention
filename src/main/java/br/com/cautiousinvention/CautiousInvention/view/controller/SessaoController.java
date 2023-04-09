package br.com.cautiousinvention.CautiousInvention.view.controller;

import br.com.cautiousinvention.CautiousInvention.service.SessaoService;
import br.com.cautiousinvention.CautiousInvention.shared.SessaoDTO;
import br.com.cautiousinvention.CautiousInvention.view.model.SessaoRequest;
import br.com.cautiousinvention.CautiousInvention.view.model.SessaoResponse;
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
@RequestMapping("/sessoes")
public class SessaoController {

    @Autowired
    SessaoService sessaoService;

    @GetMapping
    public ResponseEntity<List<SessaoResponse>> obterSessoes() {
        List<SessaoDTO> sessoes = sessaoService.obterTodasSessoes();
        List<SessaoResponse> resposta = sessoes.stream().map(sessaoDto -> new ModelMapper().map(sessaoDto, SessaoResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SessaoResponse> cadastrarSessao(@RequestBody SessaoRequest sessaoReq) {
        SessaoDTO sessaoDto = new ModelMapper().map(sessaoReq, SessaoDTO.class);
        sessaoDto = sessaoService.criarSessao(sessaoDto);
        SessaoResponse resposta = new ModelMapper().map(sessaoDto, SessaoResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SessaoResponse>> obterSessaoPorId(@PathVariable Integer id) {
        Optional<SessaoDTO> dto = sessaoService.obterSessaoPorId(id);
        SessaoResponse resposta = new ModelMapper().map(dto.get(), SessaoResponse.class);
        return new ResponseEntity<>(Optional.of(resposta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarSessao(@PathVariable Integer id) {
        sessaoService.deletarSessao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessaoResponse> atualizarSessao(@PathVariable Integer id, @RequestBody SessaoRequest sessaoReq) {
        SessaoDTO sessaoDto = new ModelMapper().map(sessaoReq, SessaoDTO.class);
        sessaoDto = sessaoService.atualizarSessao(id, sessaoDto);
        SessaoResponse resposta = new ModelMapper().map(sessaoDto, SessaoResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
