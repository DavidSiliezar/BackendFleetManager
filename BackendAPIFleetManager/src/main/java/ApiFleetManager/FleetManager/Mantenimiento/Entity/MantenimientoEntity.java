package ApiFleetManager.FleetManager.Mantenimiento.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_MANTENIMIENTO")
public class MantenimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MANTENIMIENTO")
    private Integer idMantenimiento;
    @Column(name = "NOMBREMANTENIMIENTO")
    private String nombremantenimiento;
    @Column(name = "COSTO")
    private Double costo;
    @Column(name = "FK_TIPOMANTENIMIENTO")
    private Integer fkTipomantenimiento;
}
