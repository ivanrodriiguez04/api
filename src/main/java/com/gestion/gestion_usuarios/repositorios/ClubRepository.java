package com.gestion.gestion_usuarios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.gestion_usuarios.daos.ClubDao;

public interface ClubRepository extends JpaRepository<ClubDao, Long> {

    /**
     * Busca un club por su email.
     * 
     * @param emailClub el email del club.
     * @return el club encontrado con ese email.
     */
    ClubDao findByEmailClub(String emailClub);

    /**
     * Verifica si existe un club con el email proporcionado.
     * 
     * @param emailClub el email del club.
     * @return true si el club con ese email existe, false en caso contrario.
     */
    boolean existsByEmailClub(String emailClub);

    /**
     * Busca un club por su ID.
     * 
     * @param idClub el ID del club.
     * @return un {@link Optional} con el club encontrado, vacío si no se encuentra.
     */
    Optional<ClubDao> findById(Long idClub);

}
