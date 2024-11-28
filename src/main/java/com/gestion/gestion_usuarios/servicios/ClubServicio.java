package com.gestion.gestion_usuarios.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.gestion_usuarios.daos.ClubDao;
import com.gestion.gestion_usuarios.dtos.RegistroClubDto;
import com.gestion.gestion_usuarios.repositorios.ClubRepository;

@Service
public class ClubServicio {
	
	 @Autowired
	 private ClubRepository clubRepository; // Inyectamos el repositorio
	 
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

	 //Metodo que se asegura si el correo ya se encuentra registrado
	 public boolean emailExistsClub(String emailCLub) {
		 return clubRepository.existsByEmailClub(emailCLub);
	 }
	 
	// Método para registrar un nuevo club
	    public void registroClub(RegistroClubDto clubDto) {
	        ClubDao club = new ClubDao();
	        club.setNombreClub(clubDto.getNombreClub());
	        club.setEmailClub(clubDto.getEmailClub());
	        club.setPasswordClub(clubDto.getPasswordClub()); // Asegúrate de encriptar la contraseña antes de guardarla

	        clubRepository.save(club); // Guardar el nuevo club en la base de datos
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	  /**
     * Da de alta un nuevo club en la base de datos.
     * <p>
     * Convierte un objeto {@link RegistroClubDto} a {@link ClubDao} y lo guarda en la base de datos.
     * </p>
     *
     * @param clubDto datos del club que se desea dar de alta
     */

    @Transactional
    public void altaClub(RegistroClubDto clubDto) {
        // Convertir ClubDto a Club sin establecer idClub
        ClubDao club = new ClubDao();
        club.setNombreClub(clubDto.getNombreClub());
        club.setEmailClub(clubDto.getEmailClub());
        club.setPasswordClub(clubDto.getPasswordClub());
        club.setSedeClub(clubDto.getSedeClub());
        
        // Guardar el club en la base de datos
        clubRepository.save(club);
    }

    /**
     * Elimina un club existente de la base de datos usando el nombre del club.
     *
     * @param nombreClub nombre del club que se desea eliminar
     */

    @Transactional
    public void eliminarClub(String nombreClub) {
        clubRepository.deleteByNombreClub(nombreClub);
    }
    
    /**
     * Modifica los campos nombre, sede e imagen de un club existente en la base de datos.
     *
     * @param idClub identificador del club a modificar
     * @param clubDto objeto DTO que contiene los nuevos datos del club
     * @return un {@link Optional<ClubDto>} con los datos actualizados si la modificación fue exitosa; de lo contrario, un Optional vacío
     */
    @Transactional
    public boolean modificarClub(long idClub, String nuevoNombre, String nuevaSede, byte[] nuevaImagen) {
        Optional<ClubDao> clubOpt = clubRepository.findByIdClub(idClub);

        if (clubOpt.isPresent()) {
            ClubDao club = clubOpt.get();
            // Modificar los campos
            if (nuevoNombre != null) club.setNombreClub(nuevoNombre);
            if (nuevaSede != null) club.setSedeClub(nuevaSede);
            if (nuevaImagen != null) club.setImagenClub(nuevaImagen);

            clubRepository.save(club); // Guardar los cambios
            return true;
        }

        return false; // Si el club no existe
    }

}
