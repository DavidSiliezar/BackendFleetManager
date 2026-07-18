package ApiFleetManager.FleetManager.Asignacionvehiculo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AsignacionvehiculoDTO {
    private Integer idAsignacion;
    @NotNull(message = "El usuario es obligatorio")
    private Integer fkUsuario;
    @NotNull(message = "El vehículo es obligatorio")
    private Integer fkVehiculo;
    @NotNull(message = "La fecha de asignación es obligatoria")
    private LocalDate fechaasignacion;
    private LocalDateTime horasalida;
    private LocalDateTime horaentrega;
    private Double kilometrajeinicial;
    private Double kilometrajefinal;
    private String observaciones;
}
