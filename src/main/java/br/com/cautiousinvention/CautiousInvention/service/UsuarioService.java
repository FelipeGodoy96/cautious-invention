package br.com.cautiousinvention.CautiousInvention.service;

import br.com.cautiousinvention.CautiousInvention.model.Treino;
import br.com.cautiousinvention.CautiousInvention.model.Usuario;
import br.com.cautiousinvention.CautiousInvention.model.exception.BadRequestException;
import br.com.cautiousinvention.CautiousInvention.model.exception.ResourceNotFoundException;
import br.com.cautiousinvention.CautiousInvention.repository.TreinoRepository;
import br.com.cautiousinvention.CautiousInvention.repository.UsuarioRepository;
import br.com.cautiousinvention.CautiousInvention.shared.UsuarioDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TreinoRepository treinoRepository;

    @Transactional
    public List<UsuarioDTO> obterTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> new ModelMapper().map(usuario, UsuarioDTO.class)).collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> obterUsuarioPorId(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Usuário especificado não encontrado");
        }
        UsuarioDTO dto = new ModelMapper().map(usuario.get(), UsuarioDTO.class);
        return Optional.of(dto);
    }

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDto) {
        usuarioDto.setId(null); // Limpa ID para garantir cadastro de novo usuário.
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setIdade(usuarioDto.getIdade());
        usuario.setSexo(usuarioDto.getSexo());
        usuario.setPeso(usuarioDto.getPeso());
        usuario.setAltura(usuarioDto.getAltura());
        Set <Treino> localizados = new HashSet<>();
//        usuarioDto.getTreinos_id().stream().forEach(element -> localizados.add(element));
        System.out.println(treinoRepository.getReferenceById(1));
        usuarioDto.getTreinos_id().stream().forEach(id -> {
            if (treinoRepository.existsById(id)) {
                localizados.add(treinoRepository.getReferenceById(id));
            }
        });
        if (localizados.isEmpty()) {
            throw new BadRequestException("Nenhum treino com os IDs informados foi encontrado.");
        }
        System.out.println(localizados);
        usuario.setTreinos(localizados);
        usuario = usuarioRepository.save(usuario);

        // o retorno tem que ser um DTO, realizar processo inverso.
        Set<Integer> treinosRetornados = new HashSet<>();
        usuario.getTreinos().stream().map(treino -> treinosRetornados.add(treino.getId()));
        UsuarioDTO usuarioRetornado = new ModelMapper().map(usuario, UsuarioDTO.class);
        usuarioRetornado.setTreinos_id(treinosRetornados);
        return usuarioRetornado;
    }

    public void deletarUsuario(Integer id) {
        if (obterUsuarioPorId(id).isEmpty()) {
            throw new ResourceNotFoundException("Usuário especificado não existe");
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO atualizarUsuario(Integer id, UsuarioDTO usuarioDto) {
        if (obterUsuarioPorId(id).isEmpty()) {
            throw new BadRequestException("Usuário especificado não existe");
        }
        usuarioDto.setId(id);
        Usuario usuario = new ModelMapper().map(usuarioDto, Usuario.class);
        usuarioRepository.deleteById(id);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new ModelMapper().map(usuarioAtualizado, UsuarioDTO.class);
    }

//    private void mapearDtoParaEntidade(UsuarioDTO usuarioDto, )
}
