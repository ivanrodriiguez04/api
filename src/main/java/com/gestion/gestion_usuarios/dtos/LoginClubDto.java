package com.gestion.gestion_usuarios.dtos;

/**
 * Clase DTO (Data Transfer Object) que representa los datos de login de un club.
 * Esta clase es utilizada para transferir los datos necesarios para la autenticación
 * de un club, como su ID, email y contraseña.
 */
public class LoginClubDto {
    
    /** Identificador único del club. */
    private Long idClub;
    
    /** Email de contacto del club. */
    private String emailClub;
    
    /** Contraseña para autenticar al club. */
    private String passwordClub;

    // ============================
    // Getters y Setters
    // ============================

    /**
     * Obtiene el ID del club.
     *
     * @return El ID del club.
     */
    public Long getIdClub() {
        return idClub;
    }

    /**
     * Establece el ID del club.
     *
     * @param idClub El ID del club.
     */
    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    /**
     * Obtiene el email del club.
     *
     * @return El email del club.
     */
    public String getEmailClub() {
        return emailClub;
    }

    /**
     * Establece el email del club.
     *
     * @param emailClub El email del club.
     */
    public void setEmailClub(String emailClub) {
        this.emailClub = emailClub;
    }

    /**
     * Obtiene la contraseña del club.
     *
     * @return La contraseña del club.
     */
    public String getPasswordClub() {
        return passwordClub;
    }

    /**
     * Establece la contraseña del club.
     *
     * @param passwordClub La contraseña del club.
     */
    public void setPasswordClub(String passwordClub) {
        this.passwordClub = passwordClub;
    }
}
