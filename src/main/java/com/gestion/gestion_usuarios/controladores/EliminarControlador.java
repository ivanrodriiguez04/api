package com.gestion.gestion_usuarios.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.gestion_usuarios.daos.UsuarioDao;
import com.gestion.gestion_usuarios.servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/eliminar")
public class EliminarControlador {
	@Autowired
    private UsuarioServicio usuarioServicio;

	// Método para buscar usuarios por nombre (subcadena)
    @GetMapping("/buscarUsuario")
    public ResponseEntity<List<UsuarioDao>> buscarUsuarioByName(@RequestParam String nombreUsuario) {
        List<UsuarioDao> users = usuarioServicio.findUsersByName(nombreUsuario);
        return ResponseEntity.ok(users);
    }
}