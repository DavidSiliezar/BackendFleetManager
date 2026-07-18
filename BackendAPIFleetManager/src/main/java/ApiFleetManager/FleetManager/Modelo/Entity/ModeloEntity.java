package ApiFleetManager.FleetManager.Modelo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_MODELO")
public class ModeloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODELO")
    private Integer idModelo;
    @Column(name = "NOMBREMODELO")
    private String nombremodelo;
    @Column(name = "FK_MARCA")
    private Integer fkMarca;
}
