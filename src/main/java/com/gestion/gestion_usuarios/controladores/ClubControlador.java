package com.gestion.gestion_usuarios.controladores;

import java.io.IOException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.gestion_usuarios.dtos.RegistroClubDto;
import com.gestion.gestion_usuarios.servicios.ClubServicio;

import jakarta.persistence.EntityNotFoundException;

public class ClubControlador {
	
	private final ClubServicio clubServicio = new ClubServicio();
	/**
     * Endpoint para crear un nuevo club.
     * <p>
     * Recibe un {@link RegistroClubDto} en el cuerpo de la solicitud, y si es exitoso,
     * devuelve una respuesta HTTP 201 (Created). En caso de error, devuelve
     * un mensaje de error con el estado correspondiente.
     * </p>
     *
     * @param clubDto datos del club a crear
     * @return ResponseEntity con el mensaje de éxito o error
     */
    @PostMapping
    public ResponseEntity<String> crearClub(@RequestBody RegistroClubDto clubDto) {
        try {
            clubServicio.altaClub(clubDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Club creado con éxito.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el club: " + e.getMessage());
        }
    }
    
    /**
     * Endpoint para eliminar un club por su nombre.
     * <p>
     * Si el club existe, lo elimina y devuelve un mensaje de éxito con el estado HTTP 200 (OK).
     * Si el club no es encontrado, devuelve un estado HTTP 404 (Not Found).
     * </p>
     *
     * @param nombreClub nombre del club a eliminar
     * @return ResponseEntity con el mensaje de éxito o error
     */
    @DeleteMapping("/{nombreClub}")
    public ResponseEntity<String> eliminarClub(@PathVariable String nombreClub) {
        try {
            eliminarClub(nombreClub);
            return ResponseEntity.ok("Club eliminado con éxito.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club no encontrado: " + nombreClub);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el club: " + e.getMessage());
        }
    }
    
    /**
     * Endpoint para modificar los campos nombre, sede e imagen de un club.
     * <p>
     * Recibe el identificador del club a modificar y los datos actualizados (nombre, sede e imagen).
     * Si la modificación es exitosa, devuelve un estado HTTP 200 (OK).
     * Si el club no existe, devuelve un estado HTTP 404 (Not Found).
     * </p>
     *
     * @param idClub identificador del club a modificar
     * @param nuevoNombre nuevo nombre del club
     * @param nuevaSede nueva sede del club
     * @param nuevaImagen nueva imagen del club (como archivo en multipart)
     * @return ResponseEntity con el mensaje de éxito o error
     */
    @PutMapping("/{idClub}")
    public ResponseEntity<String> modificarClub(
            @PathVariable long idClub,
            @RequestParam(required = false) String nuevoNombre,
            @RequestParam(required = false) String nuevaSede,
            @RequestParam(required = false) MultipartFile nuevaImagen) {

        try {
            byte[] imagenBytes = null;

            if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
                imagenBytes = nuevaImagen.getBytes(); // Convertir la imagen a bytes
            }

            boolean exito = clubServicio.modificarClub(idClub, nuevoNombre, nuevaSede, imagenBytes);

            if (exito) {
                return ResponseEntity.ok("Club modificado con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Club no encontrado.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la imagen.");
        }
    }

}
