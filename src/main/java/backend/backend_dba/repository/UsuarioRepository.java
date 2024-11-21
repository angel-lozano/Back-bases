package backend.backend_dba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_dba.entity.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository <Usuario, String>{
    public List<Usuario> findByUsuario(String usuario);
}
