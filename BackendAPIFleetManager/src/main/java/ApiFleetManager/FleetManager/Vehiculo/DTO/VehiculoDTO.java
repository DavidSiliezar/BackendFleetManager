package ApiFleetManager.FleetManager.Vehiculo.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VehiculoDTO {
    private Integer idVehiculo;
    @NotBlank(message = "La placa es obligatoria")
    @Pattern(regexp = "^[PCMNTR][1-9][0-9]{0,2}-[0-9]{1,3}$", message = "Formato de placa inválido (ej: P123-456)")
    private String placa;
    @NotNull(message = "El kilometraje es obligatorio")
    @DecimalMin(value = "0.0", message = "El kilometraje no puede ser negativo")
    private Double kilometraje;
    @NotBlank(message = "El número VIN es obligatorio")
    @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "El VIN debe tener 17 caracteres alfanuméricos válidos")
    private String numerovin;
    @NotBlank(message = "La tarjeta de circulación es obligatoria")
    @Pattern(regexp = "^[A-Z0-9-]{5,30}$", message = "Formato de tarjeta de circulación inválido")
    private String tarjetacirculacion;
    @NotNull(message = "El tipo de vehículo es obligatorio")
    private Integer fkTipo;
    @NotNull(message = "El estado del vehículo es obligatorio")
    private Integer fkEstado;
}
