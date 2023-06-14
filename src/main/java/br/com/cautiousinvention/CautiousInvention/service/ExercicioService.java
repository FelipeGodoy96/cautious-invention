package br.com.cautiousinvention.CautiousInvention.service;

import br.com.cautiousinvention.CautiousInvention.model.Exercicio;
import br.com.cautiousinvention.CautiousInvention.model.exception.BadRequestException;
import br.com.cautiousinvention.CautiousInvention.model.exception.ResourceNotFoundException;
import br.com.cautiousinvention.CautiousInvention.repository.ExercicioRepository;
import br.com.cautiousinvention.CautiousInvention.shared.ExercicioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<ExercicioDTO> obterTodosExercicios() {
        List<Exercicio> exercicios = exercicioRepository.findAll();
        return exercicios.stream().map(exercicio -> new ModelMapper().map(exercicio, ExercicioDTO.class)).collect(Collectors.toList());
    }

    public Optional<ExercicioDTO> obterExercicioPorId(Integer id) {
        Optional<Exercicio> exercicio = exercicioRepository.findById(id);
        if (exercicio.isEmpty()) {
            throw new ResourceNotFoundException("Exercicio especificado não encontrado");
        }
        ExercicioDTO dto = new ModelMapper().map(exercicio.get(), ExercicioDTO.class);
        return Optional.of(dto);
    }

    public ExercicioDTO criarExercicio(ExercicioDTO exercicioDto) {
        exercicioDto.setId(null); // Limpa ID para garantir cadastro de novo exercicio.
        Exercicio exercicio = new ModelMapper().map(exercicioDto, Exercicio.class);
        exercicio = exercicioRepository.save(exercicio);
        return new ModelMapper().map(exercicio, ExercicioDTO.class);
    }

    public void deletarExercicio(Integer id) {
        if (obterExercicioPorId(id).isEmpty()) {
            throw new ResourceNotFoundException("Exercicio especificado não existe");
        }
        exercicioRepository.deleteById(id);
    }

    public ExercicioDTO atualizarExercicio(Integer id, ExercicioDTO exercicioDto) {
        if (obterExercicioPorId(id).isEmpty()) {
            throw new BadRequestException("Exercicio especificado não existe");
        }
        exercicioDto.setId(id);
        Exercicio exercicio = new ModelMapper().map(exercicioDto, Exercicio.class);
        Exercicio exercicioAtualizado = exercicioRepository.save(exercicio);
        return new ModelMapper().map(exercicioAtualizado, ExercicioDTO.class);
    }

}
