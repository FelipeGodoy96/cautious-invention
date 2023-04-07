package br.com.cautiousinvention.CautiousInvention.repository;

import br.com.cautiousinvention.CautiousInvention.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
