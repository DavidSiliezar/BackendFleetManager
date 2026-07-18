package ApiFleetManager.FleetManager.Rol.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RolDTO {
    private Integer idRol;
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 25, message = "El nombre del rol no puede superar 25 caracteres")
    private String nombrerol;
}
