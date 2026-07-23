package ApiFleetManager.FleetManager.Estado.Service;

import ApiFleetManager.FleetManager.Estado.DTO.EstadoDTO;
import ApiFleetManager.FleetManager.Estado.Entity.EstadoEntity;
import ApiFleetManager.FleetManager.Estado.Repository.EstadoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public EstadoDTO insertarDatos(@Valid EstadoDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        EstadoEntity entity = convertirAEntity(jsonData);
        EstadoEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private EstadoDTO convertirADTO(EstadoEntity entitySave) {
        EstadoDTO objDTO = new EstadoDTO();
        objDTO.setIdEstado(entitySave.getIdEstado());
        objDTO.setNombreestado(entitySave.getNombreestado());
        return objDTO;
    }

    private EstadoEntity convertirAEntity(@Valid EstadoDTO jsonData) {
        EstadoEntity objEntity = new EstadoEntity();
        objEntity.setNombreestado(jsonData.getNombreestado());
        return objEntity;
    }

    public List<EstadoDTO> listarTodos() {
        List<EstadoEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public EstadoDTO buscarPorId(Integer id) {
        Optional<EstadoEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public EstadoDTO buscarPorNombre(String nombre) {
        Optional<EstadoEntity> entidadOpcional = repo.findByNombreestado(nombre);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public EstadoDTO actualizarInfo(Integer id, @Valid EstadoDTO dto) {
        Optional<EstadoEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            EstadoEntity entidad = entidadOpcional.get();
            entidad.setNombreestado(dto.getNombreestado());
            EstadoEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
