package backend.backend_dba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_dba.entity.Estado;

@Repository("estadoRepository")
public interface EstadoRepository extends JpaRepository <Estado, Integer> {
    public List<Estado> findByIdestado(int idestado);
}
