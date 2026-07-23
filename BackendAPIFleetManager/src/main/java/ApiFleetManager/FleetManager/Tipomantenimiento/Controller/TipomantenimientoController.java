package ApiFleetManager.FleetManager.Tipomantenimiento.Controller;

import ApiFleetManager.FleetManager.Tipomantenimiento.DTO.TipomantenimientoDTO;
import ApiFleetManager.FleetManager.Tipomantenimiento.Service.TipomantenimientoService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tipomantenimientos")
@CrossOrigin
public class TipomantenimientoController {

    private final TipomantenimientoService service;

    public TipomantenimientoController(TipomantenimientoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TipomantenimientoDTO>> crear(@Valid @RequestBody TipomantenimientoDTO json){
        TipomantenimientoDTO objDTO = service.insertarDatos(json);
        if (objDTO == null){
            log.warn("Intento de inserción fallido " + json);
            ApiResponse<TipomantenimientoDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        log.info("Nuevos datos ingresados " + objDTO);
        ApiResponse<TipomantenimientoDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipomantenimientoDTO>>> obtenerTodos(){
        List<TipomantenimientoDTO> listaDTO = service.listarTodos();
        if (listaDTO != null && !listaDTO.isEmpty()){
            ApiResponse<List<TipomantenimientoDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
            return ResponseEntity.ok(respuestaExitosa);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "No hay datos por mostrar", new java.util.ArrayList<>()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipomantenimientoDTO>> obtenerPorId(@PathVariable Integer id){
        TipomantenimientoDTO dto = service.buscarPorId(id);
        if (dto != null){
            ApiResponse<TipomantenimientoDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<TipomantenimientoDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ApiResponse<TipomantenimientoDTO>> obtenerPorNombre(@PathVariable String nombre){
        TipomantenimientoDTO dto = service.buscarPorNombre(nombre);
        if (dto != null){
            ApiResponse<TipomantenimientoDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<TipomantenimientoDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TipomantenimientoDTO>> eliminar(@PathVariable Integer id) {
        boolean respuesta = service.eliminarInfo(id);
        if (respuesta) {
            ApiResponse<TipomantenimientoDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<TipomantenimientoDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipomantenimientoDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody TipomantenimientoDTO dto) {
        TipomantenimientoDTO objdto = service.actualizarInfo(id, dto);
        if (objdto == null) {
            ApiResponse<TipomantenimientoDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        }
        ApiResponse<TipomantenimientoDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
        return ResponseEntity.ok(respuestaExitosa);
    }
}
