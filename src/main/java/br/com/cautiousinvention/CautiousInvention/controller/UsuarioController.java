package br.com.cautiousinvention.CautiousInvention.controller;

import br.com.cautiousinvention.CautiousInvention.service.UsuarioService;
import br.com.cautiousinvention.CautiousInvention.shared.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obterUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.obterTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioDTO>> obterUsuarioPorId(@PathVariable Integer id) {
        Optional<UsuarioDTO> usuario = usuarioService.obterUsuarioPorId(id);
        return new ResponseEntity<>(Optional.of(usuario.get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
        usuario = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuario) {
        usuario = usuarioService.atualizarUsuario(id, usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
