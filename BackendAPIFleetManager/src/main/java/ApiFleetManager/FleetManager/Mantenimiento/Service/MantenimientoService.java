package ApiFleetManager.FleetManager.Mantenimiento.Service;

import ApiFleetManager.FleetManager.Mantenimiento.DTO.MantenimientoDTO;
import ApiFleetManager.FleetManager.Mantenimiento.Entity.MantenimientoEntity;
import ApiFleetManager.FleetManager.Mantenimiento.Repository.MantenimientoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository repo;

    public MantenimientoDTO insertarDatos(@Valid MantenimientoDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        MantenimientoEntity entity = convertirAEntity(jsonData);
        MantenimientoEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private MantenimientoDTO convertirADTO(MantenimientoEntity entitySave) {
        MantenimientoDTO objDTO = new MantenimientoDTO();
        objDTO.setIdMantenimiento(entitySave.getIdMantenimiento());
        objDTO.setNombremantenimiento(entitySave.getNombremantenimiento());
        objDTO.setCosto(entitySave.getCosto());
        objDTO.setFkTipomantenimiento(entitySave.getFkTipomantenimiento());
        return objDTO;
    }

    private MantenimientoEntity convertirAEntity(@Valid MantenimientoDTO jsonData) {
        MantenimientoEntity objEntity = new MantenimientoEntity();
        objEntity.setNombremantenimiento(jsonData.getNombremantenimiento());
        objEntity.setCosto(jsonData.getCosto());
        objEntity.setFkTipomantenimiento(jsonData.getFkTipomantenimiento());
        return objEntity;
    }

    public List<MantenimientoDTO> listarTodos() {
        List<MantenimientoEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MantenimientoDTO buscarPorId(Integer id) {
        Optional<MantenimientoEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public MantenimientoDTO buscarPorNombre(String nombre) {
        Optional<MantenimientoEntity> entidadOpcional = repo.findByNombremantenimiento(nombre);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public MantenimientoDTO actualizarInfo(Integer id, @Valid MantenimientoDTO dto) {
        Optional<MantenimientoEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            MantenimientoEntity entidad = entidadOpcional.get();
            entidad.setNombremantenimiento(dto.getNombremantenimiento());
            entidad.setCosto(dto.getCosto());
            entidad.setFkTipomantenimiento(dto.getFkTipomantenimiento());
            MantenimientoEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
