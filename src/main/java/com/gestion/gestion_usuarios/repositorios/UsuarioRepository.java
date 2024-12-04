package com.gestion.gestion_usuarios.repositorios;
  // Asegúrate de que sea la entidad
import com.gestion.gestion_usuarios.daos.UsuarioDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDao, Long> {

    // Método para encontrar usuarios cuyo nombre contiene una subcadena (insensible a mayúsculas/minúsculas)
    List<UsuarioDao> findByNombreUsuarioContainingIgnoreCase(String nombreUsuario);

    UsuarioDao findByEmailUsuario(String emailUsuario);

    boolean existsByEmailUsuario(String emailUsuario); // Método para revisar si el email ya está registrado
}
