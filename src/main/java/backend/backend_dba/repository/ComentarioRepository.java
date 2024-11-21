package backend.backend_dba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_dba.entity.Comentario;
import java.util.List;

@Repository("comentarioRepository")
public interface ComentarioRepository extends JpaRepository <Comentario, Integer> {
    List<Comentario> findByIdcomentario(int idcomentario);
}
