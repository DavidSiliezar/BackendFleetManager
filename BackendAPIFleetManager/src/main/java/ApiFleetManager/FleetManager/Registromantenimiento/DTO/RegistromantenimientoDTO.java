package ApiFleetManager.FleetManager.Registromantenimiento.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistromantenimientoDTO {
    private Integer idRegistromantenimiento;
    @NotNull(message = "La fecha de entrada es obligatoria")
    private java.time.LocalDate fechaentrada;
    private java.time.LocalDate fechasalida;
    @Size(max = 500, message = "Las observaciones no pueden superar 500 caracteres")
    private String observaciones;
    @NotNull(message = "El vehículo es obligatorio")
    private Integer fkVehiculo;
    @NotNull(message = "El taller es obligatorio")
    private Integer fkTaller;
    private Integer fkAccidente;
}
