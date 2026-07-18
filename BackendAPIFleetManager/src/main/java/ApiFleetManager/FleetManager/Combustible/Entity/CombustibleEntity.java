package ApiFleetManager.FleetManager.Combustible.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_COMBUSTIBLE")
public class CombustibleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMBUSTIBLE")
    private Integer idCombustible;
    @Column(name = "GALONESTOTALES")
    private Double galonestotales;
    @Column(name = "FECHACARGA")
    private java.time.LocalDate fechacarga;
    @Column(name = "COSTO")
    private Double costo;
    @Column(name = "IMAGENFACTURA")
    private String imagenfactura;
    @Column(name = "FK_ASIGNACION")
    private Integer fkAsignacion;
}
