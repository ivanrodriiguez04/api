package com.gestion.gestion_usuarios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Asegúrate de que sea la entidad
import com.gestion.gestion_usuarios.daos.UsuarioDao;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDao, Long> {

	// Método para encontrar usuarios cuyo nombre contiene una subcadena (insensible
	// a mayúsculas/minúsculas)

	UsuarioDao findByEmailUsuario(String emailUsuario);

	boolean existsByEmailUsuario(String emailUsuario); // Método para revisar si el email ya está registrado

	Optional<UsuarioDao> findById(Long id); // Método para encontrar por ID

}
