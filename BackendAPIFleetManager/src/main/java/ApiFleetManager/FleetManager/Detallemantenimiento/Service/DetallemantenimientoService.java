package ApiFleetManager.FleetManager.Detallemantenimiento.Service;

import ApiFleetManager.FleetManager.Detallemantenimiento.DTO.DetallemantenimientoDTO;
import ApiFleetManager.FleetManager.Detallemantenimiento.Entity.DetallemantenimientoEntity;
import ApiFleetManager.FleetManager.Detallemantenimiento.Repository.DetallemantenimientoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DetallemantenimientoService {

    @Autowired
    private DetallemantenimientoRepository repo;

    public DetallemantenimientoDTO insertarDatos(@Valid DetallemantenimientoDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        DetallemantenimientoEntity entity = convertirAEntity(jsonData);
        DetallemantenimientoEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private DetallemantenimientoDTO convertirADTO(DetallemantenimientoEntity entitySave) {
        DetallemantenimientoDTO objDTO = new DetallemantenimientoDTO();
        objDTO.setIdDetallemantenimiento(entitySave.getIdDetallemantenimiento());
        objDTO.setCantidad(entitySave.getCantidad());
        objDTO.setCosto(entitySave.getCosto());
        objDTO.setFkRegistromantenimiento(entitySave.getFkRegistromantenimiento());
        objDTO.setFkMantenimiento(entitySave.getFkMantenimiento());
        return objDTO;
    }

    private DetallemantenimientoEntity convertirAEntity(@Valid DetallemantenimientoDTO jsonData) {
        DetallemantenimientoEntity objEntity = new DetallemantenimientoEntity();
        objEntity.setCantidad(jsonData.getCantidad());
        objEntity.setCosto(jsonData.getCosto());
        objEntity.setFkRegistromantenimiento(jsonData.getFkRegistromantenimiento());
        objEntity.setFkMantenimiento(jsonData.getFkMantenimiento());
        return objEntity;
    }

    public List<DetallemantenimientoDTO> listarTodos() {
        List<DetallemantenimientoEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public DetallemantenimientoDTO buscarPorId(Integer id) {
        Optional<DetallemantenimientoEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public DetallemantenimientoDTO actualizarInfo(Integer id, @Valid DetallemantenimientoDTO dto) {
        Optional<DetallemantenimientoEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            DetallemantenimientoEntity entidad = entidadOpcional.get();
            entidad.setCantidad(dto.getCantidad());
            entidad.setCosto(dto.getCosto());
            entidad.setFkRegistromantenimiento(dto.getFkRegistromantenimiento());
            entidad.setFkMantenimiento(dto.getFkMantenimiento());
            DetallemantenimientoEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
