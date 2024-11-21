package backend.backend_dba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.backend_dba.entity.Administrador;
import backend.backend_dba.repository.AdministradorRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/administrador")
@CrossOrigin
public class AdministradorController {

    @Autowired
    AdministradorRepository administradorRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/buscar")
    public List<Administrador> getBuscar(@RequestParam(required = false) String param) {
        return administradorRepository.findAll();
    }

    @PostMapping("/guardar")
    public Administrador postGuardar(@RequestBody Administrador administrador) {
        String encryptedPassword = passwordEncoder.encode(administrador.getPassword());
        administrador.setPassword(encryptedPassword);
        return administradorRepository.save(administrador);
    }

    @PutMapping("/actualizar/{correo}")
    public ResponseEntity<Administrador> putActualizar(@PathVariable("correo") String correo,
            @RequestBody Administrador administrador) {
        Administrador administradorExistente = administradorRepository.findByCorreo(correo).stream().findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador no encontrado"));

        administradorExistente.setNombre(administrador.getNombre());
        administradorExistente.setApellido(administrador.getApellido());
        administradorExistente.setCarnet(administrador.getCarnet());
        administradorExistente.setCurso(administrador.getCurso());

        administradorExistente.setPassword(passwordEncoder.encode(
                Optional.ofNullable(administrador.getPassword()).orElse(administradorExistente.getPassword())));

        Administrador administradorActualizado = administradorRepository.save(administradorExistente);
        return ResponseEntity.ok(administradorActualizado);
    }

    @DeleteMapping("/eliminar/{correo}")
    public void eliminar(@PathVariable("correo") String correo) {
        administradorRepository.deleteById(correo);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Administrador administrador) {
        Administrador administradorEncontrado = administradorRepository.findByCorreo(administrador.getCorreo())
            .stream().findFirst().orElse(null);
    
        if (administradorEncontrado == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Correo o contraseña incorrectos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(administrador.getPassword(), administradorEncontrado.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Login exitoso");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Correo o contraseña incorrectos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
}
