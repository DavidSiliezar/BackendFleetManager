package ApiFleetManager.FleetManager.Vehiculo.Controller;

import ApiFleetManager.FleetManager.Vehiculo.DTO.VehiculoDTO;
import ApiFleetManager.FleetManager.Vehiculo.Service.VehiculoService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin
public class VehiculoController {

    private final VehiculoService service;

    public VehiculoController(VehiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehiculoDTO>> crear(@Valid @RequestBody VehiculoDTO json){
        try{
            VehiculoDTO objDTO = service.insertarDatos(json);
            if (objDTO == null){
                log.warn("Intento de inserción fallido " + json);
                ApiResponse<VehiculoDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }
            log.info("Nuevos datos ingresados " + objDTO);
            ApiResponse<VehiculoDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
            return ResponseEntity.ok(respuesta);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<VehiculoDTO> respuesta = new ApiResponse<>(false, "Error crítico: "+ e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VehiculoDTO>>> obtenerTodos(){
        try{
            List<VehiculoDTO> listaDTO = service.listarTodos();
            if (listaDTO != null && !listaDTO.isEmpty()){
                ApiResponse<List<VehiculoDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<List<VehiculoDTO>> respuestaNoData = new ApiResponse<>(true , "No hay datos por mostrar" , new java.util.ArrayList<>());
            return ResponseEntity.ok(respuestaNoData);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<List <VehiculoDTO>> respuestaError = new ApiResponse<>(false , "No se pudieron obtener los datos" , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VehiculoDTO>> obtenerPorId(@PathVariable Integer id){
        try{
            VehiculoDTO dto = service.buscarPorId(id);
            if (dto != null){
                ApiResponse<VehiculoDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<VehiculoDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<VehiculoDTO> respuestaError = new ApiResponse<>(false , "No se pudo completar la búsqueda del id " + id , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<VehiculoDTO>> eliminar(@PathVariable Integer id) {
        try {
            boolean respuesta = service.eliminarInfo(id);
            if (respuesta) {
                ApiResponse<VehiculoDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<VehiculoDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<VehiculoDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VehiculoDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody VehiculoDTO dto) {
        try {
            VehiculoDTO objdto = service.actualizarInfo(id, dto);
            if (objdto == null) {
                ApiResponse<VehiculoDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaNoRealizado);
            }
            ApiResponse<VehiculoDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
            return ResponseEntity.ok(respuestaExitosa);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<VehiculoDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
