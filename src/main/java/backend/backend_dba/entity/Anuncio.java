package backend.backend_dba.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ANUNCIO")
public class Anuncio implements Serializable {
    
    @Id
    @Column(name = "IDANUNCIO")
    private int idanuncio;

    @Column(name = "TEXTO")
    private String texto;

    @Lob
    @Column(name = "IMAGEN")
    private byte[] imagen;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "MARCA")
    private String marca;

}
