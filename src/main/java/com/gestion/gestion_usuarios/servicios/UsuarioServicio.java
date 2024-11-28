package com.gestion.gestion_usuarios.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.gestion_usuarios.daos.UsuarioDao;
import com.gestion.gestion_usuarios.dtos.RegistroUsuarioDto;
import com.gestion.gestion_usuarios.repositorios.UsuarioRepository;

@Service
public class UsuarioServicio {

	@Autowired
	private UsuarioRepository usuarioRepository; // Inyectamos el repositorio

	public ResponseEntity<String> validarCredenciales(String emailUsuario, String passwordUsuario) {
		// Intentamos recuperar al usuario por su email
		UsuarioDao usuario = usuarioRepository.findByEmailUsuario(emailUsuario); // Usamos la entidad Usuario

		if (usuario == null) {
			System.out.println("Usuario no encontrado para el email: " + emailUsuario);
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}

		System.out.println("Usuario encontrado: " + usuario.getEmailUsuario());
		System.out.println("Contraseña almacenada: " + usuario.getPasswordUsuario());
		System.out.println("Contraseña recibida: " + passwordUsuario);

		// Aquí comparamos la contraseña directamente
		if (!passwordUsuario.equals(usuario.getPasswordUsuario())) {
			System.out.println("Contraseña incorrecta");
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}

		// Si las credenciales son correctas, utilizamos el rol directamente desde la
		// base de datos
		String rol = usuario.getRol(); // Obtenemos el rol desde la entidad
		System.out.println("Rol recuperado desde la base de datos: " + rol);

		return ResponseEntity.ok(rol); // Devolvemos el rol
	}

	// Metodo que se asegura si el correo ya se encuentra registrado
	public boolean emailExistsUsuario(String emailUsuario) {
		return usuarioRepository.existsByEmailUsuario(emailUsuario);
	}

	// Método para registrar un nuevo club
	public void registroUsuario(RegistroUsuarioDto usuarioDto) {
	    if (usuarioDto.getEmailUsuario() == null || usuarioDto.getEmailUsuario().isEmpty()) {
	        throw new IllegalArgumentException("El email es obligatorio.");
	    }

	    UsuarioDao usuario = new UsuarioDao();
	    usuario.setNicknameUsuario(usuarioDto.getNicknameUsuario());
	    usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
	    usuario.setDniUsuario(usuarioDto.getDniUsuario());
	    usuario.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());
	    usuario.setEmailUsuario(usuarioDto.getEmailUsuario());
	    usuario.setPasswordUsuario(usuarioDto.getPasswordUsuario()); // Asegúrate de encriptar la contraseña
	    usuario.setRol("usuario");

	    usuarioRepository.save(usuario);
	}

	/**
	 * Elimina un usuario existente de la base de datos usando el nickname del
	 * usuario.
	 *
	 * @param nicknameUsuario nickname del usuario que se desea eliminar
	 */
	@Transactional
	public void eliminarUsuario(String emailUsuario) {
		usuarioRepository.deleteByEmailUsuario(emailUsuario);
	}

	/**
	 * Modifica los campos nombre, teléfono e imagen de un usuario existente en la
	 * base de datos.
	 *
	 * @param idUsuario     identificador del usuario a modificar
	 * @param nuevoNombre   nuevo nombre del usuario (opcional)
	 * @param nuevoTelefono nuevo teléfono del usuario (opcional)
	 * @param nuevaFoto     nueva foto del usuario (opcional)
	 * @return true si la modificación fue exitosa; de lo contrario, false
	 */
	@Transactional
	public boolean modificarUsuario(long idUsuario, String nuevoNombre, String nuevoTelefono, byte[] nuevaFoto) {
		Optional<UsuarioDao> usuarioOpt = usuarioRepository.findById(idUsuario);

		if (usuarioOpt.isPresent()) {
			UsuarioDao usuario = usuarioOpt.get();
			// Modificar los campos si se proporcionan valores no nulos
			if (nuevoNombre != null)
				usuario.setNombreUsuario(nuevoNombre);
			if (nuevoTelefono != null)
				usuario.setTelefonoUsuario(nuevoTelefono);
			if (nuevaFoto != null)
				usuario.setFotoUsuario(nuevaFoto);

			usuarioRepository.save(usuario); // Guardar los cambios
			return true;
		}

		return false; // Si el usuario no existe
	}
}
