package com.gestion.gestion_usuarios.dtos;

public class LoginClubDto {
	private Long idClub;
    private String emailClub;
    private String passwordClub;
    
    // Getters y setters
	public Long getIdClub() {
		return idClub;
	}
	public void setIdClub(Long idClub) {
		this.idClub = idClub;
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

}
