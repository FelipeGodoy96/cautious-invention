package br.com.cautiousinvention.CautiousInvention.service;

import br.com.cautiousinvention.CautiousInvention.model.Serie;
import br.com.cautiousinvention.CautiousInvention.model.exception.BadRequestException;
import br.com.cautiousinvention.CautiousInvention.model.exception.ResourceNotFoundException;
import br.com.cautiousinvention.CautiousInvention.repository.SerieRepository;
import br.com.cautiousinvention.CautiousInvention.shared.SerieDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> obterTodasSeries() {
        List<Serie> series = serieRepository.findAll();
        return series.stream().map(serie -> new ModelMapper().map(serie, SerieDTO.class)).collect(Collectors.toList());
    }

    public Optional<SerieDTO> obterSeriePorId(Integer id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if (serie.isEmpty()) {
            throw new ResourceNotFoundException("Série especificada não encontrada");
        }
        SerieDTO dto = new ModelMapper().map(serie.get(), SerieDTO.class);
        return Optional.of(dto);
    }

    public SerieDTO criarSerie(SerieDTO serieDto) {
        serieDto.setId(null); // Limpa ID para garantir cadastro de novo serie.
        Serie serie = new ModelMapper().map(serieDto, Serie.class);
        serie = serieRepository.save(serie);
        return new ModelMapper().map(serie, SerieDTO.class);
    }

    public void deletarSerie(Integer id) {
        if (obterSeriePorId(id).isEmpty()) {
            throw new ResourceNotFoundException("Série especificada não existe");
        }
        serieRepository.deleteById(id);
    }

    public SerieDTO atualizarSerie(Integer id, SerieDTO serieDto) {
        if (obterSeriePorId(id).isEmpty()) {
            throw new BadRequestException("Série especificada não existe");
        }
        serieDto.setId(id);
        Serie serie = new ModelMapper().map(serieDto, Serie.class);
        Serie serieAtualizado = serieRepository.save(serie);
        return new ModelMapper().map(serieAtualizado, SerieDTO.class);
    }

}
