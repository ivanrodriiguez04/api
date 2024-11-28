package com.gestion.gestion_usuarios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.gestion_usuarios.dtos.RegistroClubDto;
import com.gestion.gestion_usuarios.dtos.RegistroUsuarioDto;
import com.gestion.gestion_usuarios.servicios.ClubServicio;
import com.gestion.gestion_usuarios.servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/registro")
public class RegistroControlador {
	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private ClubServicio clubServicio;

	@PostMapping("/usuario")
	public ResponseEntity<String> registroUsuario(@RequestBody RegistroUsuarioDto usuarioDto) {
	    try {
	        if (usuarioDto.getEmailUsuario() == null || usuarioDto.getEmailUsuario().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email es obligatorio.");
	        }

	        if (usuarioServicio.emailExistsUsuario(usuarioDto.getEmailUsuario())) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya está registrado.");
	        }

	        usuarioServicio.registroUsuario(usuarioDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
	    }
	}

	@PostMapping("/club")
	public ResponseEntity<String> registroClub(@RequestBody RegistroClubDto clubDto) {
		try {
	        if (clubDto.getEmailClub() == null || clubDto.getEmailClub().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email es obligatorio.");
	        }

	        if (usuarioServicio.emailExistsUsuario(clubDto.getEmailClub())) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya está registrado.");
	        }

	        clubServicio.registroClub(clubDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Club registrado exitosamente.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
	    }
	}
}
