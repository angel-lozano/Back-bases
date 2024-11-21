package backend.backend_dba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_dba.entity.Reservacion;
import backend.backend_dba.repository.ReservacionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/reservacion")
@CrossOrigin
public class ReservacionController {
    
    @Autowired
    ReservacionRepository reservacionRepository;

    @GetMapping("/buscar")
    public List<Reservacion> getBuscar(@RequestParam(required = false) String param) {
        return reservacionRepository.findAll();
    }
    
    @PostMapping("/guardar")
    public Reservacion postGuardar(@RequestBody Reservacion reservacion) {
        return reservacionRepository.save(reservacion);
    }

    @PutMapping("/actualizar/{idreservacion}")
    public ResponseEntity<Reservacion> putActualizar(@PathVariable("idreservacion") int idreservacion, @RequestBody Reservacion reservacion) {
        Reservacion reservacionExistente = reservacionRepository.findById(idreservacion).orElseThrow(() -> 
            new RuntimeException("Reservación con ID " + idreservacion + " no encontrada."));
        reservacionExistente.setIdvehiculo(reservacion.getIdvehiculo());
        reservacionExistente.setIdestado(reservacion.getIdestado());
        reservacionExistente.setUsuario(reservacion.getUsuario());
        reservacionExistente.setPrecio(reservacion.getPrecio());
        reservacionExistente.setTexto(reservacion.getTexto());
        Reservacion reservacionActualizada = reservacionRepository.save(reservacionExistente);
        return ResponseEntity.ok(reservacionActualizada);
    }

    @DeleteMapping("/eliminar/{idreservacion}")
    public ResponseEntity<Void> eliminar(@PathVariable("idreservacion") int idreservacion) {
        if (!reservacionRepository.existsById(idreservacion)) {
            return ResponseEntity.notFound().build();
        }
        reservacionRepository.deleteById(idreservacion);
        return ResponseEntity.noContent().build();
    }
}
