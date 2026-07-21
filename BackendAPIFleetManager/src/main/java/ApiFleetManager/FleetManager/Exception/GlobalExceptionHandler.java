package ApiFleetManager.FleetManager.Exception;

import ApiFleetManager.FleetManager.Response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("Conflicto de integridad de datos (Foreign Key o Constraints): ", ex);
        String mensaje = "El registro que intentas asignar no existe o hay un conflicto de datos (ej: dato duplicado). Revisa las dependencias y el orden de inserción.";

        if (ex.getCause() != null && ex.getCause().getCause() != null) {
            String causeMessage = ex.getCause().getCause().getMessage();
            if (causeMessage.contains("ORA-02291")) {
                mensaje = "La llave foránea (Foreign Key) enviada no existe en la tabla principal. Primero debes insertar el dato del cual depende este registro.";
            } else if (causeMessage.contains("ORA-00001")) {
                mensaje = "Violación de restricción única (UNIQUE constraint). El dato ya existe en la base de datos.";
            } else if (causeMessage.contains("ORA-02290")) {
                mensaje = "Los datos no cumplen con el formato o condición requerida (Restricción CHECK violada). Por favor, revisa el formato.";
            } else if (causeMessage.contains("ORA-01400")) {
                mensaje = "Se intentó insertar un valor nulo en una columna que no lo permite (NOT NULL constraint).";
            } else if (causeMessage.contains("ORA-32795")) {
                mensaje = "No se puede insertar un valor en una columna generada automáticamente (GENERATED ALWAYS). No envíes el ID en el cuerpo del POST.";
            }
        }

        ApiResponse<Object> response = new ApiResponse<>(false, mensaje, null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Argumento inválido: ", ex);
        ApiResponse<Object> response = new ApiResponse<>(false, "Datos incorrectos: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Error de validación de Spring: ", ex);

        String mensajes = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> "[" + fe.getField() + "] " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ApiResponse<Object> response = new ApiResponse<>(false, "Validación fallida: " + mensajes, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException ex) {
        log.error("Error de negocio: ", ex);
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex) {
        log.error("Excepción no controlada: ", ex);
        ApiResponse<Object> response = new ApiResponse<>(false, "Ocurrió un error interno en el servidor: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
