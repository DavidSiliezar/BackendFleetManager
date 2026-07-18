package ApiFleetManager.FleetManager.Marca.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MarcaDTO {
    private Integer idMarca;
    @NotBlank(message = "El nombre de la marca es obligatorio")
    @Size(max = 50, message = "El nombre de la marca no puede superar 50 caracteres")
    private String nombremarca;
}
