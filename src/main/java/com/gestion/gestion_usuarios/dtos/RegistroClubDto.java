package com.gestion.gestion_usuarios.dtos;

/**
 * Data Transfer Object (DTO) para la entidad Club.
 * <p>
 * Esta clase se utiliza para transferir datos entre la API y la capa de
 * servicio, aislando la lógica de la entidad principal y proporcionando solo
 * los datos necesarios.
 * </p>
 */
public class RegistroClubDto {

	private long idClub;
	private String nombreClub;
	private String emailClub;
	private String passwordClub;
	private String sedeClub;

	// Getters & Setters
	public long getIdClub() {
		return idClub;
	}

	public void setIdClub(long idClub) {
		this.idClub = idClub;
	}

	public String getNombreClub() {
		return nombreClub;
	}

	public void setNombreClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}

	public String getEmailClub() {
		return emailClub;
	}

	public void setEmailClub(String emailClub) {
		this.emailClub = emailClub;
	}

	public String getPasswordClub() {
		return passwordClub;
	}

	public void setPasswordClub(String passwordClub) {
		this.passwordClub = passwordClub;
	}

	public String getSedeClub() {
		return sedeClub;
	}

	public void setSedeClub(String sedeClub) {
		this.sedeClub = sedeClub;
	}

}
