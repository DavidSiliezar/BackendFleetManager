package ApiFleetManager.FleetManager.Usuario.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer idUsuario;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no puede superar 50 caracteres")
    private String apellido;
    @NotBlank(message = "El carnet es obligatorio")
    @Size(max = 15, message = "El carnet no puede superar 15 caracteres")
    private String carnet;
    @NotBlank(message = "El DUI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}-[0-9]{1}$", message = "El DUI debe tener el formato 00000000-0")
    private String dui;
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "El teléfono debe tener el formato 0000-0000")
    private String telefono;
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Size(max = 50, message = "El correo no puede superar 50 caracteres")
    private String correo;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String passwordhash;
    @NotNull(message = "El rol es obligatorio")
    private Integer fkRol;
}
