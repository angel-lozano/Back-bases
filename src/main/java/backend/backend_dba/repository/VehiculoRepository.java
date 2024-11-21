package backend.backend_dba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_dba.entity.Vehiculo;

@Repository("vehiculoRepository")
public interface VehiculoRepository extends JpaRepository <Vehiculo, Integer> {
    public List<Vehiculo> findByIdvehiculo(int idvehiculo);
}
