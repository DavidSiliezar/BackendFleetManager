package ApiFleetManager.FleetManager.Registromantenimiento.Controller;

import ApiFleetManager.FleetManager.Registromantenimiento.DTO.RegistromantenimientoDTO;
import ApiFleetManager.FleetManager.Registromantenimiento.Service.RegistromantenimientoService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/registromantenimientos")
@CrossOrigin
public class RegistromantenimientoController {

    private final RegistromantenimientoService service;

    public RegistromantenimientoController(RegistromantenimientoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RegistromantenimientoDTO>> crear(@Valid @RequestBody RegistromantenimientoDTO json){
        try{
            RegistromantenimientoDTO objDTO = service.insertarDatos(json);
            if (objDTO == null){
                log.warn("Intento de inserción fallido " + json);
                ApiResponse<RegistromantenimientoDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }
            log.info("Nuevos datos ingresados " + objDTO);
            ApiResponse<RegistromantenimientoDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
            return ResponseEntity.ok(respuesta);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<RegistromantenimientoDTO> respuesta = new ApiResponse<>(false, "Error crítico: "+ e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RegistromantenimientoDTO>>> obtenerTodos(){
        try{
            List<RegistromantenimientoDTO> listaDTO = service.listarTodos();
            if (listaDTO != null && !listaDTO.isEmpty()){
                ApiResponse<List<RegistromantenimientoDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
                return ResponseEntity.ok(respuestaExitosa);
            }
            return ResponseEntity.ok(new ApiResponse<>(true, "No hay datos por mostrar", new java.util.ArrayList<>()));
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<List <RegistromantenimientoDTO>> respuestaError = new ApiResponse<>(false , "No se pudieron obtener los datos" , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RegistromantenimientoDTO>> obtenerPorId(@PathVariable Integer id){
        try{
            RegistromantenimientoDTO dto = service.buscarPorId(id);
            if (dto != null){
                ApiResponse<RegistromantenimientoDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<RegistromantenimientoDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<RegistromantenimientoDTO> respuestaError = new ApiResponse<>(false , "No se pudo completar la búsqueda del id " + id , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RegistromantenimientoDTO>> eliminar(@PathVariable Integer id) {
        try {
            boolean respuesta = service.eliminarInfo(id);
            if (respuesta) {
                ApiResponse<RegistromantenimientoDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<RegistromantenimientoDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<RegistromantenimientoDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RegistromantenimientoDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody RegistromantenimientoDTO dto) {
        try {
            RegistromantenimientoDTO objdto = service.actualizarInfo(id, dto);
            if (objdto == null) {
                ApiResponse<RegistromantenimientoDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaNoRealizado);
            }
            ApiResponse<RegistromantenimientoDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
            return ResponseEntity.ok(respuestaExitosa);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<RegistromantenimientoDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
