package backend.backend_dba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_dba.entity.Comentario;
import backend.backend_dba.repository.ComentarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/comentario")
@CrossOrigin
public class ComentarioController {
    
    @Autowired
    ComentarioRepository comentarioRepository;

    @GetMapping("/buscar")
    public List<Comentario> getBuscar(@RequestParam(required = false) String param) {
        return comentarioRepository.findAll();
    }

    @PostMapping("/guardar")
    public Comentario postGuardar(@RequestBody Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @PutMapping("/actualizar/{idcomentario}")
    public ResponseEntity<Comentario> putActualizar(@PathVariable("idcomentario") int idcomentario, @RequestBody Comentario comentario) {
        Comentario comentarioExistente = comentarioRepository.findById(idcomentario).orElseThrow(() -> 
            new RuntimeException("Comentario con ID " + idcomentario + " no encontrado."));
        comentarioExistente.setIdvehiculo(comentario.getIdvehiculo());
        comentarioExistente.setIdestado(comentario.getIdestado());
        comentarioExistente.setUsuario(comentario.getUsuario());
        comentarioExistente.setFecha(comentario.getFecha());
        comentarioExistente.setTexto(comentario.getTexto());
        Comentario comentarioActualizado = comentarioRepository.save(comentarioExistente);
        return ResponseEntity.ok(comentarioActualizado);
    }

    @DeleteMapping("/eliminar/{idcomentario}")
    public ResponseEntity<Void> eliminar(@PathVariable("idcomentario") int idcomentario) {
        if (!comentarioRepository.existsById(idcomentario)) {
            return ResponseEntity.notFound().build();
        }
        comentarioRepository.deleteById(idcomentario);
        return ResponseEntity.noContent().build();
    }
    
}
