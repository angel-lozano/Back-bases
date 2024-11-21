package backend.backend_dba.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {
    
    @Id
    @Column(name = "USUARIO")
    private String usuario;
    
    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "DPI")
    private String dpi;

}
