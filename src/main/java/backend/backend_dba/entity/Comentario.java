package backend.backend_dba.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "COMENTARIO")
public class Comentario implements Serializable {
    
    @Id
    @Column(name = "IDCOMENTARIO")
    private int idcomentario;

    @Column(name = "IDVEHICULO")
    private int idvehiculo;

    @Column(name = "IDESTADO")
    private int idestado;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "TEXTO")
    private String Texto;

}
