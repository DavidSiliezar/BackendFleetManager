package ApiFleetManager.FleetManager.Taller.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TallerDTO {
    private Integer idTaller;
    @NotBlank(message = "El nombre del taller es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombretaller;
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 250, message = "La dirección no puede superar 250 caracteres")
    private String direccion;
    @NotBlank(message = "El responsable es obligatorio")
    @Size(max = 100, message = "El responsable no puede superar 100 caracteres")
    private String responsable;
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "El teléfono debe tener el formato 0000-0000")
    private String telefonotaller;
}
