package backend.backend_dba.entity;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "VEHICULO")
public class Vehiculo implements Serializable {

    @Id
    @Column(name = "IDVEHICULO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private int idvehiculo;

    @Column(name = "IDESTADO")
    private int idestado;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "MATRICULA")
    private String matricula;

    @Column(name = "FECHAINICIO")
    private String fechainicio;

    @Column(name = "FECHAFIN")
    private String fechafin;
    
}
