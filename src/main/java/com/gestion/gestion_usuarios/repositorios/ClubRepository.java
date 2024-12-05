package com.gestion.gestion_usuarios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.gestion_usuarios.daos.ClubDao;
import com.gestion.gestion_usuarios.daos.UsuarioDao;

public interface ClubRepository extends JpaRepository<ClubDao, Long>{

	ClubDao findByEmailClub(String emailClub);  // Buscar usuario por email

	boolean existsByEmailClub(String emailClub); //Metodo para 
	
	Optional<ClubDao> findById(Long idClub); // Método para encontrar por ID


}
