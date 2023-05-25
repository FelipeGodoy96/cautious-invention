package br.com.cautiousinvention.CautiousInvention.view.controller;

import br.com.cautiousinvention.CautiousInvention.repository.TreinoRepository;
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

    @Autowired
    private TreinoRepository treinoRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obterUsuarios() {
        List<UsuarioDTO> produtos = usuarioService.obterTodosUsuarios();
        List<UsuarioResponse> resposta = produtos.stream().map(usuarioDto -> new ModelMapper().map(usuarioDto, UsuarioResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest usuarioReq) {
//        UsuarioDTO usuarioDto = new UsuarioDTO();
//        conversaoListaIdParaEntidade(usuarioDto, usuarioReq);
        UsuarioDTO usuarioDto = new ModelMapper().map(usuarioReq, UsuarioDTO.class);
        usuarioDto = usuarioService.criarUsuario(usuarioDto);
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

//    public void conversaoListaIdParaEntidade (UsuarioDTO dto, UsuarioRequest req) {
//        Set<Treino> localizados = new HashSet<>();
//        req.getTreinos_id().stream().forEach(id -> {
//            if (treinoRepository.existsById(id)) {
//                localizados.add(treinoRepository.getReferenceById(id));
//            }
//        });
//        if (localizados.isEmpty()) {
//            throw new BadRequestException("Nenhum treino com os IDs informados foi encontrado.");
//        }
//        dto.setTreinos(localizados);
//    }

//    private void conversaoListaEntidadeParaId(UsuarioDTO usuarioDto, UsuarioResponse resposta) {
//        Set<Integer> treinos_id = new HashSet<>();
//        System.out.println(usuarioDto.getTreinos());
//        System.out.println(usuarioDto.getTreinos().stream().findFirst());
//        usuarioDto.getTreinos().stream().forEach(treino -> {
//            treinos_id.add(treino.getId());
//        });
//        if (treinos_id.isEmpty()) {
//            throw new ResourceNotFoundException("Nenhum treino foi localizado.");
//        }
//        resposta.setTreinos_id(treinos_id);
//    }

}
