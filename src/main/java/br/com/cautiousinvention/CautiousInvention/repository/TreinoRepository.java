package br.com.cautiousinvention.CautiousInvention.repository;

import br.com.cautiousinvention.CautiousInvention.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Integer> {
}
