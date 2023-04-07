package br.com.cautiousinvention.CautiousInvention.repository;

import br.com.cautiousinvention.CautiousInvention.model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {
}
