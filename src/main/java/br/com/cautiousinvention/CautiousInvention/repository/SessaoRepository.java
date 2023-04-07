package br.com.cautiousinvention.CautiousInvention.repository;

import br.com.cautiousinvention.CautiousInvention.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
}
