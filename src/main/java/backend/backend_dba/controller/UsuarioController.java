package backend.backend_dba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import backend.backend_dba.entity.Usuario;
import backend.backend_dba.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/buscar")
    public List<Usuario> getBuscar(@RequestParam(required = false) String param) {
        return usuarioRepository.findAll();
    }

    @PostMapping("/guardar")
    public Usuario postGuardar(@RequestBody Usuario usuario) {
        String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encryptedPassword);
        return usuarioRepository.save(usuario);
    }
    
    @PutMapping("/actualizar/{usuario}")
    public ResponseEntity<Usuario> putActualizar(@PathVariable("usuario") String usuario,
            @RequestBody Usuario usuarioRequest) {
        Usuario usuarioExistente = usuarioRepository.findById(usuario)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        
        usuarioExistente.setNombre(usuarioRequest.getNombre());
        usuarioExistente.setApellido(usuarioRequest.getApellido());
        usuarioExistente.setTelefono(usuarioRequest.getTelefono());
        usuarioExistente.setDpi(usuarioRequest.getDpi());

        if (usuarioRequest.getPassword() != null && !usuarioRequest.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioRequest.getPassword()));
        }
        
        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/eliminar/{usuario}")
    public void eliminar(@PathVariable("usuario") String usuario ) {
        usuarioRepository.deleteById(usuario);
    }

}
