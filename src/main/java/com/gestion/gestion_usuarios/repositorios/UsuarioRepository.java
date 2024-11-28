package com.gestion.gestion_usuarios.repositorios;
  // Asegúrate de que sea la entidad
import com.gestion.gestion_usuarios.daos.UsuarioDao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDao, Long> {
	
    UsuarioDao findByEmailUsuario(String emailUsuario);  // Buscar usuario por email

	boolean existsByEmailUsuario(String emailUsuario); //Metodo para revisar si el email ya se encuentra registrado

	
	void deleteByEmailUsuario(String emailUsuario);//borrar por nickname
	

    
}
