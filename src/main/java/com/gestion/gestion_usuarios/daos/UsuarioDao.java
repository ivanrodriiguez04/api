package com.gestion.gestion_usuarios.daos;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario", schema = "gestion")  // Especifica el esquema "gestion"
public class UsuarioDao {

	/** Identificador único del usuario, generado automáticamente. */
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID automáticamente
    @Column(name = "id_usuario", updatable = false) // Configuración de la columna en la base de datos
    private long idUsuario;

    @Column(name = "nickname_usuario", unique = true, length = 50) // Campo único y requerido
    private String nicknameUsuario;

    @Column(name = "nombre_usuario",  length = 100) // Campo requerido
    private String nombreUsuario;

    @Column(name = "dni_usuario", unique = true, length = 20) // Campo único y requerido
    private String dniUsuario;

    @Column(name = "telefono_usuario", length = 15) // Campo opcional
    private String telefonoUsuario;

    @Column(name = "foto_usuario", columnDefinition = "bytea") // Personaliza el tipo de columna
    @Basic(fetch = FetchType.LAZY) // Indica carga diferida para este campo
    private byte[] fotoUsuario;

    @Column(name = "email_usuario", unique = true, length = 150) // Campo único y requerido
    private String emailUsuario;

    @Column(name = "passwd_usuario", length = 255) // Campo requerido
    private String passwordUsuario;

    /** Rol asignado al usuario, define los permisos de acceso (ADMIN, USER, etc.). */
    @Column(name = "rol_usuario", length = 50) // Campo requerido con longitud máxima
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
