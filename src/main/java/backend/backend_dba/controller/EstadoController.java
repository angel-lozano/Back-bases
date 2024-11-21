package backend.backend_dba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_dba.entity.Estado;
import backend.backend_dba.repository.EstadoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/estado")
@CrossOrigin
public class EstadoController {
    
    @Autowired
    EstadoRepository estadoRepository;

    @GetMapping("/buscar")
    public List<Estado> getBuscar(@RequestParam(required = false) String param) {
        return estadoRepository.findAll();
    }

    @PostMapping("/guardar")
    public Estado postGuardar(@RequestBody Estado estado) {
        return estadoRepository.save(estado);
    }

    @PutMapping("/actualizar/{idestado}")
    public ResponseEntity<Estado> putMethodName(@PathVariable("idestado") int idestado, @RequestBody Estado estado) {
        Estado estadoExistente = estadoRepository.findById(idestado).orElseThrow();
        estadoExistente.setValor(estado.getValor());
        Estado estadoActualizado = estadoRepository.save(estadoExistente);
        return ResponseEntity.ok(estadoActualizado);
    }

    @DeleteMapping("/eliminar/{idestado}")
    public void eliminar(@PathVariable("idestado") int idestado) {
        estadoRepository.deleteById(idestado);
    }
    
}
