package ApiFleetManager.FleetManager.Detallemantenimiento.Controller;

import ApiFleetManager.FleetManager.Detallemantenimiento.DTO.DetallemantenimientoDTO;
import ApiFleetManager.FleetManager.Detallemantenimiento.Service.DetallemantenimientoService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/detallemantenimientos")
@CrossOrigin
public class DetallemantenimientoController {

    private final DetallemantenimientoService service;

    public DetallemantenimientoController(DetallemantenimientoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DetallemantenimientoDTO>> crear(@Valid @RequestBody DetallemantenimientoDTO json){
        DetallemantenimientoDTO objDTO = service.insertarDatos(json);
        if (objDTO == null){
            log.warn("Intento de inserción fallido " + json);
            ApiResponse<DetallemantenimientoDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        log.info("Nuevos datos ingresados " + objDTO);
        ApiResponse<DetallemantenimientoDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DetallemantenimientoDTO>>> obtenerTodos(){
        List<DetallemantenimientoDTO> listaDTO = service.listarTodos();
        if (listaDTO != null && !listaDTO.isEmpty()){
            ApiResponse<List<DetallemantenimientoDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
            return ResponseEntity.ok(respuestaExitosa);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "No hay datos por mostrar", new java.util.ArrayList<>()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DetallemantenimientoDTO>> obtenerPorId(@PathVariable Integer id){
        DetallemantenimientoDTO dto = service.buscarPorId(id);
        if (dto != null){
            ApiResponse<DetallemantenimientoDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<DetallemantenimientoDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DetallemantenimientoDTO>> eliminar(@PathVariable Integer id) {
        boolean respuesta = service.eliminarInfo(id);
        if (respuesta) {
            ApiResponse<DetallemantenimientoDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<DetallemantenimientoDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DetallemantenimientoDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody DetallemantenimientoDTO dto) {
        DetallemantenimientoDTO objdto = service.actualizarInfo(id, dto);
        if (objdto == null) {
            ApiResponse<DetallemantenimientoDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        }
        ApiResponse<DetallemantenimientoDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
        return ResponseEntity.ok(respuestaExitosa);
    }
}
