package br.com.cautiousinvention.CautiousInvention.service;

import br.com.cautiousinvention.CautiousInvention.model.Sessao;
import br.com.cautiousinvention.CautiousInvention.model.exception.BadRequestException;
import br.com.cautiousinvention.CautiousInvention.model.exception.ResourceNotFoundException;
import br.com.cautiousinvention.CautiousInvention.repository.SessaoRepository;
import br.com.cautiousinvention.CautiousInvention.shared.SessaoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    @Autowired
    SessaoRepository sessaoRepository;

    public List<SessaoDTO> obterTodasSessoes() {
        List<Sessao> sessoes = sessaoRepository.findAll();
        return sessoes.stream().map(sessao -> new ModelMapper().map(sessao, SessaoDTO.class)).collect(Collectors.toList());
    }

    public Optional<SessaoDTO> obterSessaoPorId(Integer id) {
        Optional<Sessao> sessao = sessaoRepository.findById(id);
        if (sessao.isEmpty()) {
            throw new ResourceNotFoundException("Sessão especificada não encontrada");
        }
        SessaoDTO dto = new ModelMapper().map(sessao.get(), SessaoDTO.class);
        return Optional.of(dto);
    }

    public SessaoDTO criarSessao(SessaoDTO sessaoDto) {
        sessaoDto.setId(null); // Limpa ID para garantir cadastro de novo sessao.
        Sessao sessao = new ModelMapper().map(sessaoDto, Sessao.class);
        sessao = sessaoRepository.save(sessao);
        return new ModelMapper().map(sessao, SessaoDTO.class);
    }

    public void deletarSessao(Integer id) {
        if (obterSessaoPorId(id).isEmpty()) {
            throw new ResourceNotFoundException("Sessão especificada não existe");
        }
        sessaoRepository.deleteById(id);
    }

    public SessaoDTO atualizarSessao(Integer id, SessaoDTO sessaoDto) {
        if (obterSessaoPorId(id).isEmpty()) {
            throw new BadRequestException("Sessão especificada não existe");
        }
        sessaoDto.setId(id);
        Sessao sessao = new ModelMapper().map(sessaoDto, Sessao.class);
        sessaoRepository.deleteById(id);
        Sessao sessaoAtualizado = sessaoRepository.save(sessao);
        return new ModelMapper().map(sessaoAtualizado, SessaoDTO.class);
    }

}
