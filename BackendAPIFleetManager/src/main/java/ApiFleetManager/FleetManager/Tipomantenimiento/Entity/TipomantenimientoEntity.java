package ApiFleetManager.FleetManager.Tipomantenimiento.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_TIPOMANTENIMIENTO")
public class TipomantenimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPOMANTENIMIENTO")
    private Integer idTipomantenimiento;
    @Column(name = "NOMBRETIPOM")
    private String nombretipom;
}
