package com.gestion.gestion_usuarios.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	public List<UsuarioDao> findUsersByName(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuarioContainingIgnoreCase(nombreUsuario);
    }
}