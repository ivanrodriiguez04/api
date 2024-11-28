package com.gestion.gestion_usuarios.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.gestion_usuarios.daos.ClubDao;
import com.gestion.gestion_usuarios.dtos.RegistroClubDto;
import com.gestion.gestion_usuarios.repositorios.ClubRepository;

@Service
public class ClubServicio {
    @Autowired
    private ClubRepository clubRepository;

  //Metodo que  valida las credenciales del club
  	 public ResponseEntity<String> validarCredenciales(String emailClub, String passwordClub) {
  		    // Intentamos recuperar al club por su email
  		    ClubDao club = clubRepository.findByEmailClub(emailClub);

  		    if (club == null) {
  		        System.out.println("Usuario no encontrado para el email: " + emailClub);
  		        return ResponseEntity.status(401).body("Credenciales incorrectas");
  		    }

  		    System.out.println("Usuario encontrado: " + club.getEmailClub());
  		    System.out.println("Contraseña almacenada: " + club.getPasswordClub());
  		    System.out.println("Contraseña recibida: " + passwordClub);

  		    // Comparación directa de la contraseña
  		    if (!passwordClub.equals(club.getPasswordClub())) {
  		        System.out.println("Contraseña incorrecta");
  		        return ResponseEntity.status(401).body("Credenciales incorrectas");
  		    }

  		    // Si las credenciales son correctas, devolvemos un mensaje genérico de éxito
  		    System.out.println("Credenciales correctas");
  		    return ResponseEntity.status(200).body("club");
  		}
    @Transactional
    public boolean registrarClub(RegistroClubDto clubDto) {
        if (clubDto.getEmailClub() == null || clubDto.getEmailClub().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }
        
        if (clubRepository.existsByEmailClub(clubDto.getEmailClub())) {
            return false; // El correo ya está registrado
        }

        ClubDao club = new ClubDao();
        club.setNombreClub(clubDto.getNombreClub());
        club.setSedeClub(clubDto.getSedeClub());
        club.setEmailClub(clubDto.getEmailClub());
        club.setPasswordClub(clubDto.getPasswordClub()); // Encriptación de la contraseña

        clubRepository.save(club);
        return true;
    }
}
