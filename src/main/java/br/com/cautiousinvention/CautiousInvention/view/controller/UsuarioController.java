package br.com.cautiousinvention.CautiousInvention.view.controller;

import br.com.cautiousinvention.CautiousInvention.service.UsuarioService;
import br.com.cautiousinvention.CautiousInvention.shared.UsuarioDTO;
import br.com.cautiousinvention.CautiousInvention.view.model.UsuarioRequest;
import br.com.cautiousinvention.CautiousInvention.view.model.UsuarioResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obterUsuarios() {
        List<UsuarioDTO> produtos = usuarioService.obterTodosUsuarios();
        List<UsuarioResponse> resposta = produtos.stream().map(usuarioDto -> new ModelMapper().map(usuarioDto, UsuarioResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest usuarioReq) {
        UsuarioDTO usuarioDto = new ModelMapper().map(usuarioReq, UsuarioDTO.class);
        usuarioDto = usuarioService.criarUsuario(usuarioDto);

        // talvez será necessário usar um construtor de UsuarioResponse passando o usuarioDto, invés de modelmapper.
        UsuarioResponse resposta = new ModelMapper().map(usuarioDto, UsuarioResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponse>> obterUsuarioPorId(@PathVariable Integer id) {
        Optional<UsuarioDTO> dto = usuarioService.obterUsuarioPorId(id);
        UsuarioResponse resposta = new ModelMapper().map(dto.get(), UsuarioResponse.class);
        return new ResponseEntity<>(Optional.of(resposta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioReq) {
        UsuarioDTO usuarioDto = new ModelMapper().map(usuarioReq, UsuarioDTO.class);
        usuarioDto = usuarioService.atualizarUsuario(id, usuarioDto);
        UsuarioResponse resposta = new ModelMapper().map(usuarioDto, UsuarioResponse.class);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
