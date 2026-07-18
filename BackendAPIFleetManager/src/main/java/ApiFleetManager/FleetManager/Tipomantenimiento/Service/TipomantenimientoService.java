package ApiFleetManager.FleetManager.Tipomantenimiento.Service;

import ApiFleetManager.FleetManager.Tipomantenimiento.DTO.TipomantenimientoDTO;
import ApiFleetManager.FleetManager.Tipomantenimiento.Entity.TipomantenimientoEntity;
import ApiFleetManager.FleetManager.Tipomantenimiento.Repository.TipomantenimientoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TipomantenimientoService {

    @Autowired
    private TipomantenimientoRepository repo;

    public TipomantenimientoDTO insertarDatos(@Valid TipomantenimientoDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        try{
            TipomantenimientoEntity entity = convertirAEntity(jsonData);
            TipomantenimientoEntity entitySave = repo.save(entity);
            return convertirADTO(entitySave);
        }catch (Exception e){
            log.error("Error al ingresar la información: " + e.getMessage());
            throw new RuntimeException("Error al registrar el dato");
        }
    }

    private TipomantenimientoDTO convertirADTO(TipomantenimientoEntity entitySave) {
        TipomantenimientoDTO objDTO = new TipomantenimientoDTO();
        objDTO.setIdTipomantenimiento(entitySave.getIdTipomantenimiento());
        objDTO.setNombretipom(entitySave.getNombretipom());
        return objDTO;
    }

    private TipomantenimientoEntity convertirAEntity(@Valid TipomantenimientoDTO jsonData) {
        TipomantenimientoEntity objEntity = new TipomantenimientoEntity();
        objEntity.setNombretipom(jsonData.getNombretipom());
        return objEntity;
    }

    public List<TipomantenimientoDTO> listarTodos() {
        List<TipomantenimientoEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public TipomantenimientoDTO buscarPorId(Integer id) {
        Optional<TipomantenimientoEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public TipomantenimientoDTO actualizarInfo(Integer id, @Valid TipomantenimientoDTO dto) {
        try {
            Optional<TipomantenimientoEntity> entidadOpcional = repo.findById(id);
            if (entidadOpcional.isPresent()) {
                TipomantenimientoEntity entidad = entidadOpcional.get();
                entidad.setNombretipom(dto.getNombretipom());
                TipomantenimientoEntity datosGuardados = repo.save(entidad);
                return convertirADTO(datosGuardados);
            }
            return null;
        } catch (Exception e) {
            log.error("Ocurrió un error al procesar la información");
            throw new RuntimeException("Error al actualizar el tipo de mantenimiento: " + e.getMessage());
        }
    }
}
