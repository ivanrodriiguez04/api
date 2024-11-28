package com.gestion.gestion_usuarios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.gestion_usuarios.daos.ClubDao;
import com.gestion.gestion_usuarios.daos.UsuarioDao;

public interface ClubRepository extends JpaRepository<ClubDao, Long>{

	ClubDao findByEmailClub(String emailClub);  // Buscar usuario por email

	boolean existsByEmailClub(String emailClub); //Metodo para 
	
	
	
	
	
	
	
	/**
	 * Elimina un club por su nombre.
	 *
	 * @param nombreClub El nombre del club a eliminar.
	 */
	void deleteByNombreClub(String nombreClub);


	Optional<ClubDao> findByIdClub(Long idClub); // Encuentra un club por su ID
	// Método para encontrar un club por su email

	ClubDao save(ClubDao club);

}
