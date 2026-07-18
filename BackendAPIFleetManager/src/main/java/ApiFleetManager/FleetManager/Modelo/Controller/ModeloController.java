package ApiFleetManager.FleetManager.Modelo.Controller;

import ApiFleetManager.FleetManager.Modelo.DTO.ModeloDTO;
import ApiFleetManager.FleetManager.Modelo.Service.ModeloService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/modelos")
@CrossOrigin
public class ModeloController {

    private final ModeloService service;

    public ModeloController(ModeloService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ModeloDTO>> crear(@Valid @RequestBody ModeloDTO json){
        try{
            ModeloDTO objDTO = service.insertarDatos(json);
            if (objDTO == null){
                log.warn("Intento de inserción fallido " + json);
                ApiResponse<ModeloDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }
            log.info("Nuevos datos ingresados " + objDTO);
            ApiResponse<ModeloDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
            return ResponseEntity.ok(respuesta);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<ModeloDTO> respuesta = new ApiResponse<>(false, "Error crítico: "+ e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ModeloDTO>>> obtenerTodos(){
        try{
            List<ModeloDTO> listaDTO = service.listarTodos();
            if (listaDTO != null && !listaDTO.isEmpty()){
                ApiResponse<List<ModeloDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<List<ModeloDTO>> respuestaNoData = new ApiResponse<>(true , "No hay datos por mostrar" , new java.util.ArrayList<>());
            return ResponseEntity.ok(respuestaNoData);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<List <ModeloDTO>> respuestaError = new ApiResponse<>(false , "No se pudieron obtener los datos" , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ModeloDTO>> obtenerPorId(@PathVariable Integer id){
        try{
            ModeloDTO dto = service.buscarPorId(id);
            if (dto != null){
                ApiResponse<ModeloDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<ModeloDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<ModeloDTO> respuestaError = new ApiResponse<>(false , "No se pudo completar la búsqueda del id " + id , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ApiResponse<ModeloDTO>> obtenerPorNombre(@PathVariable String nombre){
        try{
            ModeloDTO dto = service.buscarPorNombre(nombre);
            if (dto != null){
                ApiResponse<ModeloDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<ModeloDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<ModeloDTO> respuestaError = new ApiResponse<>(false , "No se pudo completar la búsqueda del nombre " + nombre , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ModeloDTO>> eliminar(@PathVariable Integer id) {
        try {
            boolean respuesta = service.eliminarInfo(id);
            if (respuesta) {
                ApiResponse<ModeloDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<ModeloDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<ModeloDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ModeloDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody ModeloDTO dto) {
        try {
            ModeloDTO objdto = service.actualizarInfo(id, dto);
            if (objdto == null) {
                ApiResponse<ModeloDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaNoRealizado);
            }
            ApiResponse<ModeloDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
            return ResponseEntity.ok(respuestaExitosa);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<ModeloDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
