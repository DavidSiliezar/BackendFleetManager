package ApiFleetManager.FleetManager.Accidente.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccidenteDTO {
    private Integer idAccidente;
    @NotNull(message = "La fecha del accidente es obligatoria")
    private java.time.LocalDate fechaaccidente;
    @NotBlank(message = "El lugar del accidente es obligatorio")
    @Size(max = 200, message = "El lugar no puede superar 200 caracteres")
    private String lugar;
    @Size(max = 500, message = "La descripción no puede superar 500 caracteres")
    private String descripcion;
    @Size(max = 250, message = "La ruta de imagen no puede superar 250 caracteres")
    private String imagen;
    @NotNull(message = "La asignación de vehículo es obligatoria")
    private Integer fkAsignacion;
}
