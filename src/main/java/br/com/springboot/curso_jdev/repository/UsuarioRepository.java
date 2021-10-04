package br.com.springboot.curso_jdev.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_jdev.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query(value = "select u from Usuario u Where trim(u.nome) like %?1%")
	List<Usuario> buscarPorNome(String name);
}
