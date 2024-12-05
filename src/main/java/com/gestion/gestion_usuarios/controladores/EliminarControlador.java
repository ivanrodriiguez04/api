package com.gestion.gestion_usuarios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.gestion_usuarios.servicios.ClubServicio;
import com.gestion.gestion_usuarios.servicios.UsuarioServicio;

/**
 * Controlador encargado de manejar las operaciones de eliminación de usuarios y clubes.
 * Proporciona endpoints para eliminar usuarios y clubes del sistema.
 */
@RestController
@RequestMapping("/api/eliminar")
public class EliminarControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ClubServicio clubServicio;

    /**
     * Endpoint para eliminar un usuario por su ID.
     *
     * @param idUsuario El ID del usuario a eliminar.
     * @return Una respuesta HTTP con el resultado de la eliminación:
     *         <ul>
     *         <li>204 (NO CONTENT): Usuario eliminado exitosamente.</li>
     *         <li>404 (NOT FOUND): Usuario no encontrado.</li>
     *         <li>500 (INTERNAL SERVER ERROR): Error al eliminar el usuario.</li>
     *         </ul>
     */
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable("id") Long idUsuario) {
        try {
            boolean eliminado = usuarioServicio.borrarUsuario(idUsuario);
            if (eliminado) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario eliminado exitosamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    /**
     * Endpoint para eliminar un club por su ID.
     *
     * @param idClub El ID del club a eliminar.
     * @return Una respuesta HTTP con el resultado de la eliminación:
     *         <ul>
     *         <li>204 (NO CONTENT): Club eliminado exitosamente.</li>
     *         <li>404 (NOT FOUND): Club no encontrado.</li>
     *         <li>500 (INTERNAL SERVER ERROR): Error al eliminar el club.</li>
     *         </ul>
     */
    @DeleteMapping("/club/{id}")
    public ResponseEntity<String> borrarClub(@PathVariable("id") Long idClub) {
        try {
            boolean eliminado = clubServicio.borrarClub(idClub); // Se asume que tienes este método en ClubServicio
            if (eliminado) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Club eliminado exitosamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club no encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el club: " + e.getMessage());
        }
    }
}
