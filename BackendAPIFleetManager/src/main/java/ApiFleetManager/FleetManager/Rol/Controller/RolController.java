package ApiFleetManager.FleetManager.Rol.Controller;

import ApiFleetManager.FleetManager.Rol.DTO.RolDTO;
import ApiFleetManager.FleetManager.Rol.Service.RolService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rols")
@CrossOrigin
public class RolController {

    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RolDTO>> crear(@Valid @RequestBody RolDTO json){
        RolDTO objDTO = service.insertarDatos(json);
        if (objDTO == null){
            log.warn("Intento de inserción fallido " + json);
            ApiResponse<RolDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        log.info("Nuevos datos ingresados " + objDTO);
        ApiResponse<RolDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RolDTO>>> obtenerTodos(){
        List<RolDTO> listaDTO = service.listarTodos();
        if (listaDTO != null && !listaDTO.isEmpty()){
            ApiResponse<List<RolDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
            return ResponseEntity.ok(respuestaExitosa);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "No hay datos por mostrar", new java.util.ArrayList<>()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RolDTO>> obtenerPorId(@PathVariable Integer id){
        RolDTO dto = service.buscarPorId(id);
        if (dto != null){
            ApiResponse<RolDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<RolDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<RolDTO>>> obtenerPorNombre(@PathVariable String nombre){
        List<RolDTO> listaDTO = service.buscarPorNombre(nombre);
        if (listaDTO != null && !listaDTO.isEmpty()){
            ApiResponse<List<RolDTO>> respuestaExitosa = new ApiResponse<>(true, "Dato encontrado", listaDTO);
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<List<RolDTO>> noEncontrado = new ApiResponse<>(false, "Datos no encontrados", new java.util.ArrayList<>());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RolDTO>> eliminar(@PathVariable Integer id) {
        boolean respuesta = service.eliminarInfo(id);
        if (respuesta) {
            ApiResponse<RolDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
            return ResponseEntity.ok(respuestaExitosa);
        }
        ApiResponse<RolDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RolDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody RolDTO dto) {
        RolDTO objdto = service.actualizarInfo(id, dto);
        if (objdto == null) {
            ApiResponse<RolDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        }
        ApiResponse<RolDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
        return ResponseEntity.ok(respuestaExitosa);
    }
}
