package backend.backend_dba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_dba.entity.Anuncio;

@RestController("anuncioRepository")
public interface AnuncioRepository extends JpaRepository <Anuncio, Integer> {
    public List<Anuncio> findByIdanuncio(int idanuncio);
}
