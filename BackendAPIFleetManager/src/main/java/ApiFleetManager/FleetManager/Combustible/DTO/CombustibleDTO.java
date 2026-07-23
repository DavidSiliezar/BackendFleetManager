package ApiFleetManager.FleetManager.Combustible.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CombustibleDTO {
    private Integer idCombustible;
    @NotNull(message = "Los galones son obligatorios")
    @DecimalMin(value = "0.01", message = "Los galones deben ser mayor a 0")
    private Double galonestotales;
    @NotNull(message = "La fecha de carga es obligatoria")
    private java.time.LocalDate fechacarga;
    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.01", message = "El costo debe ser mayor a 0")
    private Double costo;
    @NotBlank(message = "La imagen de la factura es obligatoria")
    @Size(max = 250, message = "La ruta de imagen no puede superar 250 caracteres")
    private String imagenfactura;
    @NotNull(message = "La asignación de vehículo es obligatoria")
    private Integer fkAsignacion;
}
