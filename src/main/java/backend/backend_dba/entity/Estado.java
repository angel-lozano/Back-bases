package backend.backend_dba.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ESTADO")
public class Estado {
       
    @Id
    @Column(name = "IDESTADO")
    private int idestado;

    @Column(name = "VALOR")
    private String valor;
    
}
