package ApiFleetManager.FleetManager.Registromantenimiento.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_REGISTROMANTENIMIENTO")
public class RegistromantenimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REGISTROMANTENIMIENTO")
    private Integer idRegistromantenimiento;
    @Column(name = "FECHAENTRADA")
    private java.time.LocalDate fechaentrada;
    @Column(name = "FECHASALIDA")
    private java.time.LocalDate fechasalida;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "FK_VEHICULO")
    private Integer fkVehiculo;
    @Column(name = "FK_TALLER")
    private Integer fkTaller;
    @Column(name = "FK_ACCIDENTE")
    private Integer fkAccidente;
}
