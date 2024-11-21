package backend.backend_dba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_dba.entity.Administrador;

@Repository("administradorRepository")
public interface AdministradorRepository extends JpaRepository <Administrador, String>{
    public List<Administrador> findByCorreo(String correo);
}