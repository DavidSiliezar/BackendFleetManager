package ApiFleetManager.FleetManager.Detallemantenimiento.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DetallemantenimientoDTO {
    private Integer idDetallemantenimiento;
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;
    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.0", message = "El costo no puede ser negativo")
    private Double costo;
    @NotNull(message = "El registro de mantenimiento es obligatorio")
    private Integer fkRegistromantenimiento;
    @NotNull(message = "El mantenimiento es obligatorio")
    private Integer fkMantenimiento;
}
