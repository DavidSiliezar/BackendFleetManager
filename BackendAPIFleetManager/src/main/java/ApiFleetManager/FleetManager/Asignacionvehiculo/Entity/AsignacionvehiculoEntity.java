package ApiFleetManager.FleetManager.Asignacionvehiculo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_ASIGNACIONVEHICULO")
public class AsignacionvehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASIGNACION")
    private Integer idAsignacion;
    @Column(name = "FK_USUARIO")
    private Integer fkUsuario;
    @Column(name = "FK_VEHICULO")
    private Integer fkVehiculo;
    @Column(name = "FECHAASIGNACION")
    private java.time.LocalDate fechaasignacion;
    @Column(name = "HORASALIDA")
    private java.time.LocalDateTime horasalida;
    @Column(name = "HORAENTREGA")
    private java.time.LocalDateTime horaentrega;
    @Column(name = "KILOMETRAJEINICIAL")
    private Double kilometrajeinicial;
    @Column(name = "KILOMETRAJEFINAL")
    private Double kilometrajefinal;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
}
