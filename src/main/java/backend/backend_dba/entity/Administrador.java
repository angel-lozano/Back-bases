package backend.backend_dba.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ADMINISTRADOR")
public class Administrador implements Serializable {
            
    @Id
    @Column(name = "CORREO")
    private String correo;
    
    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CARNET")
    private String carnet;

    @Column(name = "CURSO")
    private String curso;

}