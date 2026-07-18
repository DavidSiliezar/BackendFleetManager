package ApiFleetManager.FleetManager.Accidente.Controller;

import ApiFleetManager.FleetManager.Accidente.DTO.AccidenteDTO;
import ApiFleetManager.FleetManager.Accidente.Service.AccidenteService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/accidentes")
@CrossOrigin
public class AccidenteController {

    private final AccidenteService service;

    public AccidenteController(AccidenteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccidenteDTO>> crear(@Valid @RequestBody AccidenteDTO json){
        try{
            AccidenteDTO objDTO = service.insertarDatos(json);
            if (objDTO == null){
                log.warn("Intento de inserción fallido " + json);
                ApiResponse<AccidenteDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }
            log.info("Nuevos datos ingresados " + objDTO);
            ApiResponse<AccidenteDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
            return ResponseEntity.ok(respuesta);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<AccidenteDTO> respuesta = new ApiResponse<>(false, "Error crítico: "+ e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccidenteDTO>>> obtenerTodos(){
        try{
            List<AccidenteDTO> listaDTO = service.listarTodos();
            if (listaDTO != null && !listaDTO.isEmpty()){
                ApiResponse<List<AccidenteDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
                return ResponseEntity.ok(respuestaExitosa);
            }
            return ResponseEntity.ok(new ApiResponse<>(true, "No hay datos por mostrar", new java.util.ArrayList<>()));
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<List <AccidenteDTO>> respuestaError = new ApiResponse<>(false , "No se pudieron obtener los datos" , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccidenteDTO>> obtenerPorId(@PathVariable Integer id){
        try{
            AccidenteDTO dto = service.buscarPorId(id);
            if (dto != null){
                ApiResponse<AccidenteDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<AccidenteDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<AccidenteDTO> respuestaError = new ApiResponse<>(false , "No se pudo completar la búsqueda del id " + id , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AccidenteDTO>> eliminar(@PathVariable Integer id) {
        try {
            boolean respuesta = service.eliminarInfo(id);
            if (respuesta) {
                ApiResponse<AccidenteDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<AccidenteDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<AccidenteDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccidenteDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody AccidenteDTO dto) {
        try {
            AccidenteDTO objdto = service.actualizarInfo(id, dto);
            if (objdto == null) {
                ApiResponse<AccidenteDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaNoRealizado);
            }
            ApiResponse<AccidenteDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
            return ResponseEntity.ok(respuestaExitosa);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<AccidenteDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
