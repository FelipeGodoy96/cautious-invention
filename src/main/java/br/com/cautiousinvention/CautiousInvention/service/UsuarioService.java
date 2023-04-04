package br.com.cautiousinvention.CautiousInvention.service;

import br.com.cautiousinvention.CautiousInvention.model.Usuario;
import br.com.cautiousinvention.CautiousInvention.model.exception.BadRequestException;
import br.com.cautiousinvention.CautiousInvention.model.exception.ResourceNotFoundException;
import br.com.cautiousinvention.CautiousInvention.repository.UsuarioRepository;
import br.com.cautiousinvention.CautiousInvention.shared.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
        Usuario usuario = new ModelMapper().map(usuarioDto, Usuario.class);
        usuario = usuarioRepository.save(usuario);
        return new ModelMapper().map(usuario, UsuarioDTO.class);
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
}
