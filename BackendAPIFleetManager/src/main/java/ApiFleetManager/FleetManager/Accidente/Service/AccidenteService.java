package ApiFleetManager.FleetManager.Accidente.Service;

import ApiFleetManager.FleetManager.Accidente.DTO.AccidenteDTO;
import ApiFleetManager.FleetManager.Accidente.Entity.AccidenteEntity;
import ApiFleetManager.FleetManager.Accidente.Repository.AccidenteRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccidenteService {

    @Autowired
    private AccidenteRepository repo;

    public AccidenteDTO insertarDatos(@Valid AccidenteDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        AccidenteEntity entity = convertirAEntity(jsonData);
        AccidenteEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private AccidenteDTO convertirADTO(AccidenteEntity entitySave) {
        AccidenteDTO objDTO = new AccidenteDTO();
        objDTO.setIdAccidente(entitySave.getIdAccidente());
        objDTO.setFechaaccidente(entitySave.getFechaaccidente());
        objDTO.setLugar(entitySave.getLugar());
        objDTO.setDescripcion(entitySave.getDescripcion());
        objDTO.setImagen(entitySave.getImagen());
        objDTO.setFkAsignacion(entitySave.getFkAsignacion());
        return objDTO;
    }

    private AccidenteEntity convertirAEntity(@Valid AccidenteDTO jsonData) {
        AccidenteEntity objEntity = new AccidenteEntity();
        objEntity.setFechaaccidente(jsonData.getFechaaccidente());
        objEntity.setLugar(jsonData.getLugar());
        objEntity.setDescripcion(jsonData.getDescripcion());
        objEntity.setImagen(jsonData.getImagen());
        objEntity.setFkAsignacion(jsonData.getFkAsignacion());
        return objEntity;
    }

    public List<AccidenteDTO> listarTodos() {
        List<AccidenteEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public AccidenteDTO buscarPorId(Integer id) {
        Optional<AccidenteEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public AccidenteDTO actualizarInfo(Integer id, @Valid AccidenteDTO dto) {
        Optional<AccidenteEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            AccidenteEntity entidad = entidadOpcional.get();
            entidad.setFechaaccidente(dto.getFechaaccidente());
            entidad.setLugar(dto.getLugar());
            entidad.setDescripcion(dto.getDescripcion());
            entidad.setImagen(dto.getImagen());
            entidad.setFkAsignacion(dto.getFkAsignacion());
            AccidenteEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
