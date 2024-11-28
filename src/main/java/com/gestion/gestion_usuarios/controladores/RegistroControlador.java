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
	public ResponseEntity<String> registerClub(@RequestBody RegistroUsuarioDto usuarioDto) {
		if (usuarioServicio.emailExistsUsuario(usuarioDto.getEmailUsuario())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ya registrado");
		}
		usuarioServicio.registroUsuario(usuarioDto);
		return ResponseEntity.ok("success");
	}

	@PostMapping("/club")
	public ResponseEntity<String> registerClub(@RequestBody RegistroClubDto clubDto) {
		if (clubServicio.emailExistsClub(clubDto.getEmailClub())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ya registrado");
		}
		clubServicio.registroClub(clubDto);
		return ResponseEntity.ok("success");
	}
}
