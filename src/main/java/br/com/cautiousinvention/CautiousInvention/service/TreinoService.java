package br.com.cautiousinvention.CautiousInvention.service;

import br.com.cautiousinvention.CautiousInvention.model.Treino;
import br.com.cautiousinvention.CautiousInvention.model.Treino;
import br.com.cautiousinvention.CautiousInvention.model.exception.BadRequestException;
import br.com.cautiousinvention.CautiousInvention.model.exception.ResourceNotFoundException;
import br.com.cautiousinvention.CautiousInvention.repository.TreinoRepository;
import br.com.cautiousinvention.CautiousInvention.repository.TreinoRepository;
import br.com.cautiousinvention.CautiousInvention.shared.TreinoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public List<TreinoDTO> obterTodosTreinos() {
        List<Treino> treinos = treinoRepository.findAll();
        return treinos.stream().map(treino -> new ModelMapper().map(treino, TreinoDTO.class)).collect(Collectors.toList());
    }

    public Optional<TreinoDTO> obterTreinoPorId(Integer id) {
        Optional<Treino> treino = treinoRepository.findById(id);
        if (treino.isEmpty()) {
            throw new ResourceNotFoundException("Treino especificado não encontrado");
        }
        TreinoDTO dto = new ModelMapper().map(treino.get(), TreinoDTO.class);
        return Optional.of(dto);
    }

    public TreinoDTO criarTreino(TreinoDTO treinoDto) {
        treinoDto.setId(null); // Limpa ID para garantir cadastro de novo treino.
        Treino treino = new ModelMapper().map(treinoDto, Treino.class);
        treino = treinoRepository.save(treino);
        return new ModelMapper().map(treino, TreinoDTO.class);
    }

    public void deletarTreino(Integer id) {
        if (obterTreinoPorId(id).isEmpty()) {
            throw new ResourceNotFoundException("Treino especificado não existe");
        }
        treinoRepository.deleteById(id);
    }

    public TreinoDTO atualizarTreino(Integer id, TreinoDTO treinoDto) {
        if (obterTreinoPorId(id).isEmpty()) {
            throw new BadRequestException("Treino especificado não existe");
        }
        treinoDto.setId(id);
        Treino treino = new ModelMapper().map(treinoDto, Treino.class);
        treinoRepository.deleteById(id);
        Treino treinoAtualizado = treinoRepository.save(treino);
        return new ModelMapper().map(treinoAtualizado, TreinoDTO.class);
    }
}
