package com.gestion.gestion_usuarios.dtos;

/**
 * Data Transfer Object (DTO) para la entidad Club.
 * <p>
 * Esta clase se utiliza para transferir datos entre la API y la capa de
 * servicio, aislando la lógica de la entidad principal y proporcionando solo
 * los datos necesarios para registrar un club.
 * </p>
 */
public class RegistroClubDto {

    /** Identificador único del club. */
    private long idClub;
    
    /** Nombre del club. */
    private String nombreClub;
    
    /** Email de contacto del club. */
    private String emailClub;
    
    /** Contraseña de autenticación del club. */
    private String passwordClub;
    
    /** Sede del club. */
    private String sedeClub;

    // ============================
    // Getters y Setters
    // ============================

    /**
     * Obtiene el ID del club.
     *
     * @return El ID del club.
     */
    public long getIdClub() {
        return idClub;
    }

    /**
     * Establece el ID del club.
     *
     * @param idClub El ID del club.
     */
    public void setIdClub(long idClub) {
        this.idClub = idClub;
    }

    /**
     * Obtiene el nombre del club.
     *
     * @return El nombre del club.
     */
    public String getNombreClub() {
        return nombreClub;
    }

    /**
     * Establece el nombre del club.
     *
     * @param nombreClub El nombre del club.
     */
    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
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

    /**
     * Obtiene la sede del club.
     *
     * @return La sede del club.
     */
    public String getSedeClub() {
        return sedeClub;
    }

    /**
     * Establece la sede del club.
     *
     * @param sedeClub La sede del club.
     */
    public void setSedeClub(String sedeClub) {
        this.sedeClub = sedeClub;
    }

}
