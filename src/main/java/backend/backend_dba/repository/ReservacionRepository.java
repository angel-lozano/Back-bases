package backend.backend_dba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_dba.entity.Reservacion;

@Repository("reservacionRepository")
public interface ReservacionRepository extends JpaRepository<Reservacion, Integer> {
    public List<Reservacion> findByIdreservacion(int idreservacion);
}
