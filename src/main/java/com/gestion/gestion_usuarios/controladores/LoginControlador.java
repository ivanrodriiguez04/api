package com.gestion.gestion_usuarios.controladores;

import com.gestion.gestion_usuarios.dtos.LoginClubDto;
import com.gestion.gestion_usuarios.dtos.LoginUsuarioDto;
import com.gestion.gestion_usuarios.servicios.ClubServicio;
import com.gestion.gestion_usuarios.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar el proceso de autenticación de usuarios y clubes.
 * Define endpoints relacionados con la validación de credenciales.
 */
@RestController
@RequestMapping("/api/login")
public class LoginControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ClubServicio clubServicio;

    /**
     * Endpoint para autenticar un usuario.
     *
     * @param usuario el objeto {@link LoginUsuarioDto} que contiene las credenciales del usuario (email y contraseña).
     * @return una respuesta HTTP con el rol del usuario si las credenciales son correctas, 
     *         o un mensaje de error si son incorrectas.
     */
    @PostMapping("/validarUsuario")
    public ResponseEntity<String> autenticarUsuario(@RequestBody LoginUsuarioDto usuario) {
        System.out.println("Email recibido: " + usuario.getEmail());
        System.out.println("Contraseña recibida: " + usuario.getPassword());

        // Llamamos al servicio para validar las credenciales
        ResponseEntity<String> resultado = usuarioServicio.validarCredenciales(usuario.getEmail(), usuario.getPassword());

        // Verificamos si las credenciales son correctas
        if (resultado.getStatusCodeValue() == 401) {
            System.out.println("Credenciales incorrectas");
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos.");
        }

        // Comprobamos el rol directamente
        String rol = resultado.getBody().trim();  // Obtenemos el cuerpo de la respuesta

        System.out.println("Rol del usuario: " + rol);

        if ("admin".equals(rol)) {
            return ResponseEntity.status(200).body("admin");
        } else if ("usuario".equals(rol)) {
            return ResponseEntity.status(200).body("usuario");
        }

        return ResponseEntity.status(401).body("Rol desconocido.");
    }

    /**
     * Endpoint para autenticar un club.
     *
     * @param club el objeto {@link LoginClubDto} que contiene las credenciales del club (email y contraseña).
     * @return una respuesta HTTP con el rol "club" si las credenciales son correctas,
     *         o un mensaje de error si son incorrectas.
     */
    @PostMapping("/validarClub")
    public ResponseEntity<String> autenticarClub(@RequestBody LoginClubDto club) {
        System.out.println("Email recibido: " + club.getEmailClub());
        System.out.println("Contraseña recibida: " + club.getPasswordClub());

        // Llamamos al servicio para validar las credenciales
        ResponseEntity<String> resultado = clubServicio.validarCredenciales(club.getEmailClub(), club.getPasswordClub());

        // Verificamos si las credenciales son correctas
        if (resultado.getStatusCodeValue() == 401) {
            System.out.println("Credenciales incorrectas");
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos.");
        }

        return ResponseEntity.status(200).body("club");
    }
}
