package com.gestion.gestion_usuarios.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.gestion_usuarios.daos.ClubDao;
import com.gestion.gestion_usuarios.daos.UsuarioDao;
import com.gestion.gestion_usuarios.dtos.RegistroClubDto;
import com.gestion.gestion_usuarios.repositorios.ClubRepository;

@Service
public class ClubServicio {

	@Autowired
	private ClubRepository clubRepository; // Inyectamos el repositorio
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Metodo que valida las credenciales del club
	public ResponseEntity<String> validarCredenciales(String emailClub, String passwordClub) {
		ClubDao club = clubRepository.findByEmailClub(emailClub);

		if (club == null) {
			System.out.println("Club no encontrado para el email: " + emailClub);
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}

		// Comparar la contraseña ingresada (texto plano) con la almacenada (encriptada)
        if (!passwordEncoder.matches(passwordClub, club.getPasswordClub())) {
            System.out.println("Contraseña incorrecta");
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

		return ResponseEntity.ok("club");
	}

	// Metodo que se asegura si el correo ya se encuentra registrado
	public boolean emailExistsClub(String emailCLub) {
		return clubRepository.existsByEmailClub(emailCLub);
	}

	// Método para registrar un nuevo club
	public void registroClub(RegistroClubDto clubDto) {
		ClubDao club = new ClubDao();
		club.setNombreClub(clubDto.getNombreClub());
		club.setEmailClub(clubDto.getEmailClub());
		club.setSedeClub(clubDto.getSedeClub());
		club.setPasswordClub(passwordEncoder.encode(clubDto.getPasswordClub()));// Encripta la contraseña antes de
																				// guardarla

		clubRepository.save(club); // Guardar el nuevo club en la base de datos
	}
	
	/**
     * Modifica los campos nombre, teléfono e imagen de un usuario existente en la base de datos.
     *
     * @param idUsuario identificador del usuario a modificar
     * @param nuevoNombre nuevo nombre del usuario (opcional)
     * @param nuevoTelefono nuevo teléfono del usuario (opcional)
     * @param nuevaFoto nueva foto del usuario (opcional)
     * @return true si la modificación fue exitosa; de lo contrario, false
     */
	@Transactional
	public boolean modificarClub(long idClub, String nuevoNombre, String nuevaSede, byte[] nuevaFoto) {
	    Optional<ClubDao> clubOpt = clubRepository.findById(idClub);

	    if (clubOpt.isPresent()) {
	        ClubDao club = clubOpt.get();

	        System.out.println("Club encontrado: " + club);

	        // Modificar los campos si se proporcionan valores no nulos
	        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
	        	club.setNombreClub(nuevoNombre);
	            System.out.println("Actualizando nombre: " + nuevoNombre);
	        }
	        if (nuevaSede != null && !nuevaSede.isEmpty()) {
	        	club.setSedeClub(nuevaSede);
	            System.out.println("Actualizando sede: " + nuevaSede);
	        }
	        if (nuevaFoto != null && nuevaFoto.length > 0) {
	        	club.setImagenClub(nuevaFoto);
	        	System.out.println("Actualizando foto de tamaño: " + nuevaFoto.length);
	        }

	        // Guardar los cambios
	        clubRepository.save(club);
	        System.out.println("Club modificado y guardado en la base de datos.");
	        return true;
	    }

	    System.out.println("Club con id " + idClub + " no encontrado.");
	    return false;
	}
	
	public ClubDao obtenerClubPorId(long idClub) {
	    return clubRepository.findById(idClub).orElse(null);
	    // Asegúrate de que `usuarioRepository` esté configurado correctamente
	}
	public boolean borrarClub(Long idClub) {
        try {
            if (clubRepository.existsById(idClub)) {
                clubRepository.deleteById(idClub); // Elimina el club por ID
                return true;
            } else {
                return false; // Si no existe el club, retorna false
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el club: " + e.getMessage());
        }
    }
}