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
        ModeloDTO objDTO = service.insertarDatos(json);
        if (objDTO == null){
            log.warn("Intento de inserción fallido " + json);
            ApiResponse<ModeloDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        log.info("Nuevos datos ingresados " + objDTO);
        ApiResponse<ModeloDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ModeloDTO>>> obtenerTodos(){
        List<ModeloDTO> listaDTO = service.listarTodos();
        if (listaDTO != null && !listaDTO.isEmpty()){
            ApiResponse<List<ModeloDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<List<ModeloDTO>> respuestaNoData = new ApiResponse<>(true , "No hay datos por mostrar" , new java.util.ArrayList<>());
        return ResponseEntity.ok(respuestaNoData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ModeloDTO>> obtenerPorId(@PathVariable Integer id){
        ModeloDTO dto = service.buscarPorId(id);
        if (dto != null){
            ApiResponse<ModeloDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<ModeloDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<ModeloDTO>>> obtenerPorNombre(@PathVariable String nombre){
        List<ModeloDTO> listaDTO = service.buscarPorNombre(nombre);
        if (listaDTO != null && !listaDTO.isEmpty()){
            ApiResponse<List<ModeloDTO>> respuestaExitosa = new ApiResponse<>(true, "Dato encontrado", listaDTO);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<List<ModeloDTO>> noEncontrado = new ApiResponse<>(false, "Datos no encontrados", new java.util.ArrayList<>());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ModeloDTO>> eliminar(@PathVariable Integer id) {
        boolean respuesta = service.eliminarInfo(id);
        if (respuesta) {
            ApiResponse<ModeloDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<ModeloDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ModeloDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody ModeloDTO dto) {
        ModeloDTO objdto = service.actualizarInfo(id, dto);
        if (objdto == null) {
            ApiResponse<ModeloDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        }
        ApiResponse<ModeloDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
        return ResponseEntity.ok(respuestaExitosa);
    }
}
