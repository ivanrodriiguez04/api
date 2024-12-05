package com.gestion.gestion_usuarios.daos;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa la entidad Club en la base de datos.
 * <p>
 * Esta clase contiene los atributos que definen a un club, como su nombre,
 * email, contraseña, sede y una imagen. Está mapeada como una entidad JPA para 
 * facilitar su persistencia en la base de datos.
 * </p>
 */
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "clubs", schema= "gestion") // Mapea esta clase a la tabla "clubs" del esquema "gestion"
public class ClubDao {

    /** Identificador único del club, generado automáticamente. */
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID automáticamente
    @Column(name = "id_club", updatable = false) // Configura la columna de la base de datos
    private long idClub;

    @Column(name = "nombre_club", length = 100) // Campo requerido, longitud máxima 100
    private String nombreClub;

    /** Email de contacto del club. */
    @Column(name = "email_club", unique = true, length = 150) // Campo único y requerido
    private String emailClub;

    /** Contraseña del club para autenticación. */
    @Column(name = "password_club", length = 255) // Campo requerido, longitud máxima 255
    private String passwordClub;

    /** Sede del club. */
    @Column(name = "sede_club", length = 200) // Campo opcional
    private String sedeClub;

    /** Imagen del club en formato de bytes. */
    @Column(name = "logo_club", columnDefinition = "bytea") // Personaliza la definición del tipo de columna
    @Basic(fetch = FetchType.LAZY) // Indica carga diferida para este campo
    private byte[] imagenClub;

    /******************************* CONSTRUCTORES ***********************************/

    /**
     * Constructor vacío, requerido por JPA.
     * Este constructor se utiliza para la creación de instancias por parte del framework JPA.
     */
    public ClubDao() {
    }

    /**
     * Constructor para crear un nuevo club sin el ID.
     * 
     * @param nombreClub el nombre del club
     * @param emailClub el email del club
     * @param passwdClub la contraseña del club
     * @param sedeClub la sede del club
     * @param imagenClub la imagen del club en formato de bytes
     */
    public ClubDao(String nombreClub, String emailClub, String passwordClub, String sedeClub, byte[] imagenClub) {
        this.nombreClub = nombreClub;
        this.emailClub = emailClub;
        this.passwordClub = passwordClub;
        this.sedeClub = sedeClub;
        this.imagenClub = imagenClub;
    }

    /******************************* GETTERS Y SETTERS **************************************/

    /**
     * Obtiene el ID único del club.
     *
     * @return El ID del club.
     */
    public long getIdClub() {
        return idClub; // Solo tiene getter, ya que no queremos que se establezca manualmente
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

    /**
     * Obtiene la imagen del club en formato de bytes.
     *
     * @return Los bytes que representan la imagen del club.
     */
    public byte[] getImagenClub() {
        return imagenClub;
    }

    /**
     * Establece la imagen del club en formato de bytes.
     *
     * @param imagenClub Los bytes que representan la nueva imagen del club.
     */
    public void setImagenClub(byte[] imagenClub) {
        this.imagenClub = imagenClub;
    }
}
