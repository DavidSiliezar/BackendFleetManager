package ApiFleetManager.FleetManager.Modelo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ModeloDTO {
    private Integer idModelo;
    @NotBlank(message = "El nombre del modelo es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombremodelo;
    @NotNull(message = "La marca es obligatoria")
    private Integer fkMarca;
}
