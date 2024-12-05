package com.gestion.gestion_usuarios.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.gestion_usuarios.daos.ClubDao;
import com.gestion.gestion_usuarios.dtos.RegistroClubDto;
import com.gestion.gestion_usuarios.repositorios.ClubRepository;

@Service
public class ClubServicio {

	@Autowired
	private ClubRepository clubRepository; // Inyectamos el repositorio para interactuar con la base de datos
	
	@Autowired
	private PasswordEncoder passwordEncoder; // Inyectamos el codificador de contraseñas para manejar las contraseñas de manera segura

	/**
	 * Método para validar las credenciales de un club.
	 * <p>
	 * Este método toma el email y la contraseña del club, verifica si el club existe en la base de datos 
	 * y compara la contraseña ingresada con la almacenada de forma segura.
	 * </p>
	 * @param emailClub el email del club
	 * @param passwordClub la contraseña proporcionada por el club
	 * @return ResponseEntity con el resultado de la validación de las credenciales
	 */
	public ResponseEntity<String> validarCredenciales(String emailClub, String passwordClub) {
		ClubDao club = clubRepository.findByEmailClub(emailClub); // Buscar el club por su email

		if (club == null) {
			// Si el club no existe, retornar error 401
			System.out.println("Club no encontrado para el email: " + emailClub);
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}

		// Compara la contraseña ingresada (texto plano) con la almacenada (encriptada)
        if (!passwordEncoder.matches(passwordClub, club.getPasswordClub())) {
            // Si la contraseña no coincide, retornar error 401
            System.out.println("Contraseña incorrecta");
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

		// Si las credenciales son correctas, retornar una respuesta exitosa
		return ResponseEntity.ok("club");
	}

	/**
	 * Método para verificar si un club con un email específico ya está registrado.
	 * @param emailCLub el email a verificar
	 * @return true si el email ya está registrado, de lo contrario false
	 */
	public boolean emailExistsClub(String emailCLub) {
		return clubRepository.existsByEmailClub(emailCLub);
	}

	/**
	 * Método para registrar un nuevo club.
	 * <p>
	 * Recibe los datos del club, encripta su contraseña y la guarda en la base de datos.
	 * </p>
	 * @param clubDto el DTO con los datos del club a registrar
	 */
	public void registroClub(RegistroClubDto clubDto) {
		ClubDao club = new ClubDao(); // Creamos una nueva instancia de ClubDao
		club.setNombreClub(clubDto.getNombreClub()); // Asignamos el nombre del club
		club.setEmailClub(clubDto.getEmailClub()); // Asignamos el email del club
		club.setSedeClub(clubDto.getSedeClub()); // Asignamos la sede del club
		club.setPasswordClub(passwordEncoder.encode(clubDto.getPasswordClub())); // Encriptamos la contraseña antes de guardarla

		clubRepository.save(club); // Guardamos el nuevo club en la base de datos
	}
	
	/**
     * Método para modificar los campos de un club existente.
     * <p>
     * Modifica el nombre, la sede y la foto del club, si los valores proporcionados no son nulos o vacíos.
     * </p>
     * @param idClub el ID del club a modificar
     * @param nuevoNombre el nuevo nombre del club
     * @param nuevaSede la nueva sede del club
     * @param nuevaFoto la nueva foto del club
     * @return true si la modificación fue exitosa, false si no se encontró el club
     */
	@Transactional
	public boolean modificarClub(long idClub, String nuevoNombre, String nuevaSede, byte[] nuevaFoto) {
	    // Buscamos el club por su ID
	    Optional<ClubDao> clubOpt = clubRepository.findById(idClub);

	    if (clubOpt.isPresent()) {
	        ClubDao club = clubOpt.get(); // Si el club existe, lo obtenemos

	        System.out.println("Club encontrado: " + club);

	        // Modificar los campos si se proporcionan valores no nulos
	        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
	        	club.setNombreClub(nuevoNombre); // Actualizamos el nombre del club
	            System.out.println("Actualizando nombre: " + nuevoNombre);
	        }
	        if (nuevaSede != null && !nuevaSede.isEmpty()) {
	        	club.setSedeClub(nuevaSede); // Actualizamos la sede del club
	            System.out.println("Actualizando sede: " + nuevaSede);
	        }
	        if (nuevaFoto != null && nuevaFoto.length > 0) {
	        	club.setImagenClub(nuevaFoto); // Actualizamos la foto del club
	        	System.out.println("Actualizando foto de tamaño: " + nuevaFoto.length);
	        }

	        // Guardamos los cambios en la base de datos
	        clubRepository.save(club);
	        System.out.println("Club modificado y guardado en la base de datos.");
	        return true;
	    }

	    System.out.println("Club con id " + idClub + " no encontrado.");
	    return false; // Si no se encuentra el club, retornamos false
	}
	
	/**
	 * Método para obtener los detalles de un club por su ID.
	 * @param idClub el ID del club a obtener
	 * @return el club encontrado o null si no existe
	 */
	public ClubDao obtenerClubPorId(long idClub) {
	    return clubRepository.findById(idClub).orElse(null); // Retorna el club si se encuentra, o null si no
	}

	/**
	 * Método para eliminar un club por su ID.
	 * <p>
	 * Si el club existe, se elimina de la base de datos. Si no existe, retorna false.
	 * </p>
	 * @param idClub el ID del club a eliminar
	 * @return true si el club fue eliminado exitosamente, false si no existe
	 */
	public boolean borrarClub(Long idClub) {
        try {
            if (clubRepository.existsById(idClub)) {
                clubRepository.deleteById(idClub); // Elimina el club por ID
                return true;
            } else {
                return false; // Si el club no existe, retornamos false
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el club: " + e.getMessage());
        }
    }
}
