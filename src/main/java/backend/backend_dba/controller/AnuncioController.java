package backend.backend_dba.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.backend_dba.entity.Anuncio;
import backend.backend_dba.repository.AnuncioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/anuncio")
@CrossOrigin
public class AnuncioController {
    
    @Autowired
    AnuncioRepository anuncioRepository;

    @GetMapping("/buscar")
    public List<Anuncio> getBuscar(@RequestParam(required = false) String param) {
        return anuncioRepository.findAll();
    }

@PostMapping("/guardar")
public Anuncio postGuardar(@RequestParam("texto") String texto, 
                           @RequestParam("modelo") String modelo, 
                           @RequestParam("marca") String marca, 
                           @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
    Anuncio anuncio = new Anuncio();
    anuncio.setTexto(texto);
    anuncio.setModelo(modelo);
    anuncio.setMarca(marca);
    if (imagen != null && !imagen.isEmpty()) {
        try {
            anuncio.setImagen(imagen.getBytes()); // Aquí manejas la imagen como un byte[]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return anuncioRepository.save(anuncio);
}


    @PutMapping("/actualizar/{idanuncio}")
    public ResponseEntity<Anuncio> putActualizar(@PathVariable("idanuncio") int idanuncio, @RequestBody Anuncio anuncio) {
        Anuncio anuncioExistente = anuncioRepository.findById(idanuncio).orElseThrow(() -> 
            new RuntimeException("Anuncio con ID " + idanuncio + " no encontrado."));
        anuncioExistente.setTexto(anuncio.getTexto());
        anuncioExistente.setImagen(anuncio.getImagen());
        anuncioExistente.setModelo(anuncio.getModelo());
        anuncioExistente.setMarca(anuncio.getMarca());
        Anuncio anuncioActualizado = anuncioRepository.save(anuncioExistente);
        return ResponseEntity.ok(anuncioActualizado);
    }

    @DeleteMapping("/eliminar/{idanuncio}")
    public ResponseEntity<Void> eliminar(@PathVariable("idanuncio") int idanuncio) {
        if (!anuncioRepository.existsById(idanuncio)) {
            return ResponseEntity.notFound().build();
        }
        anuncioRepository.deleteById(idanuncio);
        return ResponseEntity.noContent().build();
    }
    
}
