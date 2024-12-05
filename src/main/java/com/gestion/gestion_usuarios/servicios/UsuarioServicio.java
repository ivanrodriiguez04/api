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
	private UsuarioRepository usuarioRepository; // Inyectamos el repositorio para gestionar las operaciones con la base de datos

	@Autowired
	private PasswordEncoder passwordEncoder; // Inyectamos el PasswordEncoder para encriptar y comparar contraseñas

	/**
	 * Método para validar las credenciales de un usuario.
	 * <p>
	 * Este método verifica si el usuario existe en la base de datos, compara su contraseña encriptada
	 * con la proporcionada por el cliente y, si son correctas, devuelve el rol del usuario.
	 * </p>
	 * @param emailUsuario el email del usuario
	 * @param passwordUsuario la contraseña proporcionada por el usuario
	 * @return ResponseEntity con el rol del usuario si las credenciales son correctas
	 */
	public ResponseEntity<String> validarCredenciales(String emailUsuario, String passwordUsuario) {
        // Intentamos recuperar al usuario por su email
        UsuarioDao usuario = usuarioRepository.findByEmailUsuario(emailUsuario); // Usamos la entidad Usuario

        if (usuario == null) {
            // Si el usuario no se encuentra, retornar un error 401
            System.out.println("Usuario no encontrado para el email: " + emailUsuario);
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        // Log para verificar el usuario y las contraseñas (debe evitarse en producción)
        System.out.println("Usuario encontrado: " + usuario.getEmailUsuario());
        System.out.println("Contraseña almacenada: " + usuario.getPasswordUsuario());
        System.out.println("Contraseña recibida: " + passwordUsuario);

        // Comparar la contraseña ingresada (texto plano) con la almacenada (encriptada)
        if (!passwordEncoder.matches(passwordUsuario, usuario.getPasswordUsuario())) {
            // Si la contraseña no coincide, retornamos un error 401
            System.out.println("Contraseña incorrecta");
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        // Si las credenciales son correctas, obtenemos el rol del usuario desde la base de datos
        String rol = usuario.getRol(); 
        System.out.println("Rol recuperado desde la base de datos: " + rol);

        // Retornamos el rol del usuario
        return ResponseEntity.ok(rol);
    }

	/**
	 * Método para verificar si un usuario con un email específico ya está registrado.
	 * @param emailUsuario el email del usuario a verificar
	 * @return true si el email ya está registrado, de lo contrario false
	 */
	public boolean emailExistsUsuario(String emailUsuario) {
		return usuarioRepository.existsByEmailUsuario(emailUsuario); // Retorna true si el email ya existe
	}

	/**
	 * Método para registrar un nuevo usuario en el sistema.
	 * <p>
	 * Toma un DTO con los datos del usuario, encripta la contraseña y la guarda en la base de datos.
	 * </p>
	 * @param usuarioDto el DTO con los datos del usuario a registrar
	 */
	public void registroUsuario(RegistroUsuarioDto usuarioDto) {
	    // Validación del email: no puede ser nulo o vacío
	    if (usuarioDto.getEmailUsuario() == null || usuarioDto.getEmailUsuario().isEmpty()) {
	        throw new IllegalArgumentException("El email es obligatorio.");
	    }

	    // Crear un nuevo objeto UsuarioDao con los datos del DTO
	    UsuarioDao usuario = new UsuarioDao();
	    usuario.setNicknameUsuario(usuarioDto.getNicknameUsuario());
	    usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
	    usuario.setDniUsuario(usuarioDto.getDniUsuario());
	    usuario.setTelefonoUsuario(usuarioDto.getTelefonoUsuario());
	    usuario.setEmailUsuario(usuarioDto.getEmailUsuario());

	    // Encriptar la contraseña antes de guardarla en la base de datos
	    usuario.setPasswordUsuario(passwordEncoder.encode(usuarioDto.getPasswordUsuario()));

	    // Asignar un rol por defecto ("usuario")
	    usuario.setRol("usuario");

	    // Guardar el nuevo usuario en la base de datos
	    usuarioRepository.save(usuario);
	}
	
	/**
     * Método para modificar los datos de un usuario existente en la base de datos.
     * <p>
     * Modifica los campos nombre, teléfono, DNI, rol e imagen si se proporcionan valores nuevos y no nulos.
     * </p>
     * @param idUsuario identificador del usuario a modificar
     * @param nuevoNombre el nuevo nombre del usuario
     * @param nuevoDni el nuevo DNI del usuario
     * @param nuevoTelefono el nuevo teléfono del usuario
     * @param nuevoRol el nuevo rol del usuario
     * @param nuevaFoto la nueva foto del usuario (opcional)
     * @return true si la modificación fue exitosa, false si no se encontró el usuario
     */
	@Transactional
	public boolean modificarUsuario(long idUsuario, String nuevoNombre, String nuevoDni, String nuevoTelefono, String nuevoRol, byte[] nuevaFoto) {
	    // Intentamos obtener al usuario por su ID
	    Optional<UsuarioDao> usuarioOpt = usuarioRepository.findById(idUsuario);

	    if (usuarioOpt.isPresent()) {
	        UsuarioDao usuario = usuarioOpt.get(); // Si el usuario existe, lo obtenemos

	        System.out.println("Usuario encontrado: " + usuario);

	        // Modificamos los campos si los valores proporcionados no son nulos o vacíos
	        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
	            usuario.setNombreUsuario(nuevoNombre);
	            System.out.println("Actualizando nombre: " + nuevoNombre);
	        }
	        if (nuevoDni != null && !nuevoDni.isEmpty()) {
	            usuario.setDniUsuario(nuevoDni);
	            System.out.println("Actualizando DNI: " + nuevoDni);
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

	        // Guardamos los cambios realizados en la base de datos
	        usuarioRepository.save(usuario);
	        System.out.println("Usuario modificado y guardado en la base de datos.");
	        return true;
	    }

	    // Si el usuario no existe, retornamos false
	    System.out.println("Usuario con id " + idUsuario + " no encontrado.");
	    return false;
	}
	
	/**
	 * Método para obtener un usuario por su ID.
	 * @param idUsuario el ID del usuario a obtener
	 * @return el objeto UsuarioDao si se encuentra, de lo contrario null
	 */
	public UsuarioDao obtenerUsuarioPorId(long idUsuario) {
	    return usuarioRepository.findById(idUsuario).orElse(null); // Retorna el usuario si se encuentra, o null si no existe
	}

	/**
	 * Método para eliminar un usuario por su ID.
	 * <p>
	 * Si el usuario existe, se elimina de la base de datos y retorna true. Si no existe, retorna false.
	 * </p>
	 * @param idUsuario el ID del usuario a eliminar
	 * @return true si el usuario fue eliminado correctamente, false si no existía
	 */
	public boolean borrarUsuario(Long idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario); // Elimina el usuario por ID
            return true; // Retorna true si el usuario fue eliminado
        }
        return false; // Retorna false si el usuario no existe
    }

}
