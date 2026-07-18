package ApiFleetManager.FleetManager.Estado.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EstadoDTO {
    private Integer idEstado;
    @NotBlank(message = "El nombre del estado es obligatorio")
    @Size(max = 50, message = "El nombre del estado no puede superar 50 caracteres")
    private String nombreestado;
}
