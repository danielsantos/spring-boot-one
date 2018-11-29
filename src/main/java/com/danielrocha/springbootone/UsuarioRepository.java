package com.danielrocha.springbootone;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.status = 'A'")
	List<Usuario> buscarAtivos();
	
	@Modifying
    @Transactional
	@Query("update Usuario set status = 'I' where id = :id")
	void excluirLogicamente(@Param("id") Long id);

}
