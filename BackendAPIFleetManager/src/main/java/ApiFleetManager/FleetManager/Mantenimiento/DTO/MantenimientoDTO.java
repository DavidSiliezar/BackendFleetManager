package ApiFleetManager.FleetManager.Mantenimiento.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MantenimientoDTO {
    private Integer idMantenimiento;
    @NotBlank(message = "El nombre del mantenimiento es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String nombremantenimiento;
    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.0", message = "El costo no puede ser negativo")
    private Double costo;
    @NotNull(message = "El tipo de mantenimiento es obligatorio")
    private Integer fkTipomantenimiento;
}
