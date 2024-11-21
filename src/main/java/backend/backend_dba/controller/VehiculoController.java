package backend.backend_dba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_dba.entity.Vehiculo;
import backend.backend_dba.repository.VehiculoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/vehiculo")
@CrossOrigin
public class VehiculoController {
    
    @Autowired
    VehiculoRepository vehiculoRepository;

    @GetMapping("/buscar")
    public List<Vehiculo> getBuscar(@RequestParam(required = false) String param) {
        return vehiculoRepository.findAll();
    }

    @PostMapping("/guardar")
    public Vehiculo postGuardar(@RequestBody Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @PutMapping("/actualizar/{idvehiculo}")
    public ResponseEntity<Vehiculo> putActualizar(@PathVariable("idvehiculo") int idvehiculo, @RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoExistente = vehiculoRepository.findById(idvehiculo).orElseThrow(() -> 
            new RuntimeException("Vehículo con ID " + idvehiculo + " no encontrado."));
        vehiculoExistente.setIdestado(vehiculo.getIdestado());
        vehiculoExistente.setModelo(vehiculo.getModelo());
        vehiculoExistente.setMarca(vehiculo.getMarca());
        vehiculoExistente.setColor(vehiculo.getColor());
        vehiculoExistente.setMatricula(vehiculo.getMatricula());
        vehiculoExistente.setFechainicio(vehiculo.getFechainicio());
        vehiculoExistente.setFechafin(vehiculo.getFechafin());
        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculoExistente);
        return ResponseEntity.ok(vehiculoActualizado);
    }

    @DeleteMapping("/eliminar/{idvehiculo}")
    public ResponseEntity<Void> eliminar(@PathVariable("idvehiculo") int idvehiculo) {
        if (!vehiculoRepository.existsById(idvehiculo)) {
            return ResponseEntity.notFound().build();
        }
        vehiculoRepository.deleteById(idvehiculo);
        return ResponseEntity.noContent().build();
    }
    
}
