package ApiFleetManager.FleetManager.Taller.Controller;

import ApiFleetManager.FleetManager.Taller.DTO.TallerDTO;
import ApiFleetManager.FleetManager.Taller.Service.TallerService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tallers")
@CrossOrigin
public class TallerController {

    private final TallerService service;

    public TallerController(TallerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TallerDTO>> crear(@Valid @RequestBody TallerDTO json){
        TallerDTO objDTO = service.insertarDatos(json);
        if (objDTO == null){
            log.warn("Intento de inserción fallido " + json);
            ApiResponse<TallerDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        log.info("Nuevos datos ingresados " + objDTO);
        ApiResponse<TallerDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TallerDTO>>> obtenerTodos(){
        List<TallerDTO> listaDTO = service.listarTodos();
        if (listaDTO != null && !listaDTO.isEmpty()){
            ApiResponse<List<TallerDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
            return ResponseEntity.ok(respuestaExitosa);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "No hay datos por mostrar", new java.util.ArrayList<>()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TallerDTO>> obtenerPorId(@PathVariable Integer id){
        TallerDTO dto = service.buscarPorId(id);
        if (dto != null){
            ApiResponse<TallerDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<TallerDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<TallerDTO>>> obtenerPorNombre(@PathVariable String nombre){
        List<TallerDTO> listaDTO = service.buscarPorNombre(nombre);
        if (listaDTO != null && !listaDTO.isEmpty()){
            ApiResponse<List<TallerDTO>> respuestaExitosa = new ApiResponse<>(true, "Dato encontrado", listaDTO);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<List<TallerDTO>> noEncontrado = new ApiResponse<>(false, "Datos no encontrados", new java.util.ArrayList<>());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TallerDTO>> eliminar(@PathVariable Integer id) {
        boolean respuesta = service.eliminarInfo(id);
        if (respuesta) {
            ApiResponse<TallerDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<TallerDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TallerDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody TallerDTO dto) {
        TallerDTO objdto = service.actualizarInfo(id, dto);
        if (objdto == null) {
            ApiResponse<TallerDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        }
        ApiResponse<TallerDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
        return ResponseEntity.ok(respuestaExitosa);
    }
}
