package com.gestion.gestion_usuarios.controladores;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.gestion_usuarios.dtos.RegistroUsuarioDto;
import com.gestion.gestion_usuarios.servicios.UsuarioServicio;


import jakarta.persistence.EntityNotFoundException;

/**
 * Controlador REST para manejar las operaciones relacionadas con los usuarios.
 * <p>
 * Proporciona endpoints para crear, eliminar, autenticar y modificar usuarios.
 * La ruta base para todos los endpoints es {@code /api/usuarios}.
 * </p>
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {
	
	private final UsuarioServicio usuarioServicio = new UsuarioServicio();
	
	 /**
     * Endpoint para crear un nuevo usuario.
     *
     * @param usuarioDto datos del usuario a crear
     * @return ResponseEntity con el mensaje de éxito o error
     */
    @PostMapping
    public ResponseEntity<String> altaUsuario(@RequestBody RegistroUsuarioDto usuarioDto) {
        try {
            altaUsuario(usuarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario: " + e.getMessage());
        }
    }

    /**
     * Endpoint para eliminar un usuario por su email.
     *
     * @param emailUsuario nombre del usuario a eliminar
     * @return ResponseEntity con el mensaje de éxito o error
     */
    @DeleteMapping("/{emailUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String emailUsuario) {
        try {
            eliminarUsuario(emailUsuario);
            return ResponseEntity.ok("Usuario eliminado con éxito.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado: " + emailUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }


    /**
     * Endpoint para modificar los campos nombre, teléfono e imagen de un usuario.
     *
     * @param idUsuario identificador del usuario a modificar
     * @param nuevoNombre nuevo nombre del usuario
     * @param nuevoTelefono nuevo teléfono del usuario
     * @param nuevaFoto nueva foto del usuario (como archivo en multipart)
     * @return ResponseEntity con el mensaje de éxito o error
     */
    @PutMapping("/{idUsuario}")
    public ResponseEntity<String> modificarUsuario(
            @PathVariable long idUsuario,
            @RequestParam(required = false) String nuevoNombre,
            @RequestParam(required = false) String nuevoTelefono,
            @RequestParam(required = false) MultipartFile nuevaFoto) {

        try {
            byte[] fotoBytes = null;

            if (nuevaFoto != null && !nuevaFoto.isEmpty()) {
                fotoBytes = nuevaFoto.getBytes(); // Convertir la foto a bytes
            }

            boolean exito = usuarioServicio.modificarUsuario(idUsuario, nuevoNombre, nuevoTelefono, fotoBytes);

            if (exito) {
                return ResponseEntity.ok("Usuario modificado con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la foto.");
        }
    }
}
