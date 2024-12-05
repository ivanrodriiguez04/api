package com.gestion.gestion_usuarios.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) para la entidad Usuario.
 * <p>
 * Esta clase se utiliza para transferir datos entre la API y la capa de
 * servicio, aislando la lógica de la entidad principal y proporcionando solo
 * los datos necesarios para registrar un usuario.
 * </p>
 */
public class RegistroUsuarioDto {

    /** Identificador único del usuario. */
    private long idUsuario;
    
    /** Nickname único del usuario. */
    @JsonProperty("nicknameUsuario") // Configura cómo se mapea el campo en JSON
    private String nicknameUsuario;
    
    /** Nombre completo del usuario. */
    private String nombreUsuario;
    
    /** Documento de identidad del usuario (DNI). */
    private String dniUsuario;
    
    /** Número de teléfono del usuario. */
    private String telefonoUsuario;
    
    /** Email de contacto del usuario. */
    private String emailUsuario;
    
    /** Contraseña para autenticación del usuario. */
    private String passwordUsuario;
    
    /** Rol del usuario (por ejemplo, ADMIN, USER). */
    private String rol;

    // ============================
    // Getters y Setters
    // ============================

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param idUsuario El ID del usuario.
     */
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nickname del usuario.
     *
     * @return El nickname del usuario.
     */
    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    /**
     * Establece el nickname del usuario.
     *
     * @param nicknameUsuario El nickname del usuario.
     */
    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre completo del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombreUsuario El nombre completo del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el DNI del usuario.
     *
     * @return El DNI del usuario.
     */
    public String getDniUsuario() {
        return dniUsuario;
    }

    /**
     * Establece el DNI del usuario.
     *
     * @param dniUsuario El DNI del usuario.
     */
    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    /**
     * Obtiene el teléfono del usuario.
     *
     * @return El número de teléfono del usuario.
     */
    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    /**
     * Establece el teléfono del usuario.
     *
     * @param telefonoUsuario El número de teléfono del usuario.
     */
    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    /**
     * Obtiene el email del usuario.
     *
     * @return El email del usuario.
     */
    public String getEmailUsuario() {
        return emailUsuario;
    }

    /**
     * Establece el email del usuario.
     *
     * @param emailUsuario El email del usuario.
     */
    public void setEmail(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param passwordUsuario La contraseña del usuario.
     */
    public void setPassword(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

}
