package backend.backend_dba.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "RESERVACION")
public class Reservacion implements Serializable {
    
    @Id
    @Column(name = "IDRESERVACION")
    private int idreservacion;

    @Column(name = "IDVEHICULO")
    private int idvehiculo;

    @Column(name = "IDESTADO")
    private int idestado;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "PRECIO")
    private String precio;

    @Column(name = "TEXTO")
    private String texto;
    
}
