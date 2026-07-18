package ApiFleetManager.FleetManager.Marca.Controller;

import ApiFleetManager.FleetManager.Marca.DTO.MarcaDTO;
import ApiFleetManager.FleetManager.Marca.Service.MarcaService;
import ApiFleetManager.FleetManager.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/marcas")
@CrossOrigin
public class MarcaController {

    private final MarcaService service;

    public MarcaController(MarcaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MarcaDTO>> crear(@Valid @RequestBody MarcaDTO json){
        try{
            MarcaDTO objDTO = service.insertarDatos(json);
            if (objDTO == null){
                log.warn("Intento de inserción fallido " + json);
                ApiResponse<MarcaDTO> respuesta = new ApiResponse<>(false, "No se pudo completar el proceso de inserción", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }
            log.info("Nuevos datos ingresados " + objDTO);
            ApiResponse<MarcaDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
            return ResponseEntity.ok(respuesta);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<MarcaDTO> respuesta = new ApiResponse<>(false, "Error crítico: "+ e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MarcaDTO>>> obtenerTodos(){
        try{
            List<MarcaDTO> listaDTO = service.listarTodos();
            if (listaDTO != null && !listaDTO.isEmpty()){
                ApiResponse<List<MarcaDTO>> respuestaExitosa = new ApiResponse<>(true , "Proceso completado" , listaDTO);
                return ResponseEntity.ok(respuestaExitosa);
            }
            return ResponseEntity.ok(new ApiResponse<>(true, "No hay datos por mostrar", new java.util.ArrayList<>()));
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<List <MarcaDTO>> respuestaError = new ApiResponse<>(false , "No se pudieron obtener los datos" , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MarcaDTO>> obtenerPorId(@PathVariable Integer id){
        try{
            MarcaDTO dto = service.buscarPorId(id);
            if (dto != null){
                ApiResponse<MarcaDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<MarcaDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<MarcaDTO> respuestaError = new ApiResponse<>(false , "No se pudo completar la búsqueda del id " + id , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ApiResponse<MarcaDTO>> obtenerPorNombre(@PathVariable String nombre){
        try{
            MarcaDTO dto = service.buscarPorNombre(nombre);
            if (dto != null){
                ApiResponse<MarcaDTO> respuestaExitosa = new ApiResponse<>(true , "Dato encontrado" , dto);
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<MarcaDTO> noEncontrado = new ApiResponse<>(false,"Datos no encontrados" , null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (Exception e){
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<MarcaDTO> respuestaError = new ApiResponse<>(false , "No se pudo completar la búsqueda del nombre " + nombre , null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MarcaDTO>> eliminar(@PathVariable Integer id) {
        try {
            boolean respuesta = service.eliminarInfo(id);
            if (respuesta) {
                ApiResponse<MarcaDTO> respuestaExitosa = new ApiResponse<>(true, "Dato con ID " + id + " eliminado exitosamente" , null );
                return ResponseEntity.ok(respuestaExitosa);
            }
            ApiResponse<MarcaDTO> respuestaNoRealizado = new ApiResponse<>(false, "El proceso de eliminación no se pudo completar", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaNoRealizado);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<MarcaDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MarcaDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody MarcaDTO dto) {
        try {
            MarcaDTO objdto = service.actualizarInfo(id, dto);
            if (objdto == null) {
                ApiResponse<MarcaDTO> respuestaNoRealizado = new ApiResponse<>(false, "No se pudo completar el proceso de actualización", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaNoRealizado);
            }
            ApiResponse<MarcaDTO> respuestaExitosa = new ApiResponse<>(true, "Proceso completado", objdto);
            return ResponseEntity.ok(respuestaExitosa);
        } catch (Exception e) {
            log.error("Error crítico, consulte con el administrador");
            ApiResponse<MarcaDTO> respuestaError = new ApiResponse<>(false , "Error inesperado, consulte con el administrador para solucionar el problema", null);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
