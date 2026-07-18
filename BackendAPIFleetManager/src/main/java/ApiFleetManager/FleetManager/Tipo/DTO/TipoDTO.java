package ApiFleetManager.FleetManager.Tipo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoDTO {
    private Integer idTipo;
    @NotBlank(message = "El nombre del tipo es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombretipo;
    @NotNull(message = "El modelo es obligatorio")
    private Integer fkModelo;
}
