package ApiFleetManager.FleetManager.Tipomantenimiento.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TipomantenimientoDTO {
    private Integer idTipomantenimiento;
    @NotBlank(message = "El nombre del tipo de mantenimiento es obligatorio")
    @Pattern(regexp = "Preventivo|Correctivo", message = "Solo se acepta 'Preventivo' o 'Correctivo'")
    private String nombretipom;
}
