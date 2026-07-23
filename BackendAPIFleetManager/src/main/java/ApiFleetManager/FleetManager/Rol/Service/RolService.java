package ApiFleetManager.FleetManager.Rol.Service;

import ApiFleetManager.FleetManager.Rol.DTO.RolDTO;
import ApiFleetManager.FleetManager.Rol.Entity.RolEntity;
import ApiFleetManager.FleetManager.Rol.Repository.RolRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RolService {

    @Autowired
    private RolRepository repo;

    public RolDTO insertarDatos(@Valid RolDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        RolEntity entity = convertirAEntity(jsonData);
        RolEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private RolDTO convertirADTO(RolEntity entitySave) {
        RolDTO objDTO = new RolDTO();
        objDTO.setIdRol(entitySave.getIdRol());
        objDTO.setNombrerol(entitySave.getNombrerol());
        return objDTO;
    }

    private RolEntity convertirAEntity(@Valid RolDTO jsonData) {
        RolEntity objEntity = new RolEntity();
        objEntity.setNombrerol(jsonData.getNombrerol());
        return objEntity;
    }

    public List<RolDTO> listarTodos() {
        List<RolEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public RolDTO buscarPorId(Integer id) {
        Optional<RolEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public List<RolDTO> buscarPorNombre(String nombre) {
        List<RolEntity> entidades = repo.findByNombrerol(nombre);
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public RolDTO actualizarInfo(Integer id, @Valid RolDTO dto) {
        Optional<RolEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            RolEntity entidad = entidadOpcional.get();
            entidad.setNombrerol(dto.getNombrerol());
            RolEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
