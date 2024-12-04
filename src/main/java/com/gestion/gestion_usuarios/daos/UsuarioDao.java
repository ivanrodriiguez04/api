package com.gestion.gestion_usuarios.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario", schema = "gestion")  // Especifica el esquema "gestion"
public class UsuarioDao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", updatable = false)
    private long idUsuario;

    @Column(name = "nickname_usuario", unique = true, length = 50)
    private String nicknameUsuario;

    @Column(name = "nombre_usuario", length = 100) // Esta es la propiedad que se debe usar en el repositorio
    private String nombreUsuario;

    @Column(name = "dni_usuario", unique = true, length = 20)
    private String dniUsuario;

    @Column(name = "telefono_usuario", length = 15)
    private String telefonoUsuario;

    @Column(name = "foto_usuario", columnDefinition = "bytea")
    private byte[] fotoUsuario;

    @Column(name = "email_usuario", unique = true, length = 150)
    private String emailUsuario;

    @Column(name = "passwd_usuario", length = 255)
    private String passwordUsuario;

    @Column(name = "rol_usuario", length = 50)
    private String rol;

    // ============================
    // Constructores
    // ============================

    /**
     * Constructor vacío. Necesario para JPA.
     */
    public UsuarioDao() {
    }

    /**
     * Constructor con todos los campos (excepto el ID).
     * Útil para crear nuevos objetos antes de persistirlos.
     */
    public UsuarioDao(String nicknameUsuario, String nombreUsuario, String dniUsuario, String telefonoUsuario, 
                   byte[] fotoUsuario, String emailUsuario, String passwordUsuario, String rol) {
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fotoUsuario = fotoUsuario;
        this.emailUsuario = emailUsuario;
        this.passwordUsuario = passwordUsuario;
        this.rol = rol;
    }

    /**
     * Constructor completo (incluyendo ID).
     * Útil para pruebas o cuando el ID ya está definido.
     */
    public UsuarioDao(long idUsuario, String nicknameUsuario, String nombreUsuario, String dniUsuario, 
                   String telefonoUsuario, byte[] fotoUsuario, String emailUsuario, 
                   String passwordUsuario, String rol) {
        this.idUsuario = idUsuario;
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.dniUsuario = dniUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fotoUsuario = fotoUsuario;
        this.emailUsuario = emailUsuario;
        this.passwordUsuario = passwordUsuario;
        this.rol = rol;
    }

    // ============================
    // Getters y Setters
    // ============================

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public byte[] getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(byte[] fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // ============================
    // Método toString
    // ============================

    @Override
    public String toString() {
        return "Usuario{" +
               "idUsuario=" + idUsuario +
               ", nicknameUsuario='" + nicknameUsuario + '\'' +
               ", nombreUsuario='" + nombreUsuario + '\'' +
               ", dniUsuario='" + dniUsuario + '\'' +
               ", telefonoUsuario='" + telefonoUsuario + '\'' +
               ", emailUsuario='" + emailUsuario + '\'' +
               ", rol='" + rol + '\'' +
               '}';
    }
}
