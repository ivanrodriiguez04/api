package com.gestion.gestion_usuarios.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistroUsuarioDto {

	private long idUsuario;
	@JsonProperty("nicknameUsuario")
	private String nicknameUsuario;
	private String nombreUsuario;
	private String dniUsuario;
	private String telefonoUsuario;
    private String emailUsuario;
    private String passwordUsuario;
	private String rol;
    
    // Getters y setters
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
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmail(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getPasswordUsuario() {
		return passwordUsuario;
	}
	public void setPassword(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}
