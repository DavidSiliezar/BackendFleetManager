package ApiFleetManager.FleetManager.Detallemantenimiento.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_DETALLEMANTENIMIENTO")
public class DetallemantenimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLEMANTENIMIENTO")
    private Integer idDetallemantenimiento;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Column(name = "COSTO")
    private Double costo;
    @Column(name = "FK_REGISTROMANTENIMIENTO")
    private Integer fkRegistromantenimiento;
    @Column(name = "FK_MANTENIMIENTO")
    private Integer fkMantenimiento;
}
