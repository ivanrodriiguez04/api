package com.gestion.gestion_usuarios.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.gestion_usuarios.daos.UsuarioDao;
import com.gestion.gestion_usuarios.dtos.RegistroUsuarioDto;
import com.gestion.gestion_usuarios.repositorios.UsuarioRepository;

@Service
public class UsuarioServicio {


	@Autowired
	private UsuarioRepository usuarioRepository; // Inyectamos el repositorio
	@Autowired
	private PasswordEncoder passwordEncoder;// Inyecta el PasswordEncoder




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


        // Comparar la contraseña ingresada (texto plano) con la almacenada (encriptada)
        if (!passwordEncoder.matches(passwordUsuario, usuario.getPasswordUsuario())) {
            System.out.println("Contraseña incorrecta");
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }


        // Si las credenciales son correctas, utilizamos el rol directamente desde la base de datos
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


	    // Encripta la contraseña antes de guardarla
	    usuario.setPasswordUsuario(passwordEncoder.encode(usuarioDto.getPasswordUsuario()));


	    usuario.setRol("usuario");
	    usuarioRepository.save(usuario);
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
	public boolean modificarUsuario(long idUsuario, String nuevoNombre, String nuevoDni, String nuevoTelefono, String nuevoRol,byte[] nuevaFoto) {
	    Optional<UsuarioDao> usuarioOpt = usuarioRepository.findById(idUsuario);

	    if (usuarioOpt.isPresent()) {
	        UsuarioDao usuario = usuarioOpt.get();

	        System.out.println("Usuario encontrado: " + usuario);

	        // Modificar los campos si se proporcionan valores no nulos
	        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
	            usuario.setNombreUsuario(nuevoNombre);
	            System.out.println("Actualizando nombre: " + nuevoNombre);
	        }
	        if (nuevoDni != null && !nuevoDni.isEmpty()) {
	            usuario.setDniUsuario(nuevoDni);
	            System.out.println("Actualizando dni: " + nuevoDni);
	        }
	        if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
	            usuario.setTelefonoUsuario(nuevoTelefono);
	            System.out.println("Actualizando teléfono: " + nuevoTelefono);
	        }
	        if (nuevoRol != null && !nuevoRol.isEmpty()) {
	            usuario.setRol(nuevoRol);
	            System.out.println("Actualizando rol: " + nuevoRol);
	        }
	        if (nuevaFoto != null && nuevaFoto.length > 0) {
	            usuario.setFotoUsuario(nuevaFoto);
	            System.out.println("Actualizando foto de tamaño: " + nuevaFoto.length);
	        }

	        // Guardar los cambios
	        usuarioRepository.save(usuario);
	        System.out.println("Usuario modificado y guardado en la base de datos.");
	        return true;
	    }

	    System.out.println("Usuario con id " + idUsuario + " no encontrado.");
	    return false;
	}
	
	public UsuarioDao obtenerUsuarioPorId(long idUsuario) {
	    return usuarioRepository.findById(idUsuario).orElse(null);
	    // Asegúrate de que `usuarioRepository` esté configurado correctamente
	}
	public boolean borrarUsuario(Long idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        return false;
    }

}