package ApiFleetManager.FleetManager.Asignacionvehiculo.Service;

import ApiFleetManager.FleetManager.Asignacionvehiculo.DTO.AsignacionvehiculoDTO;
import ApiFleetManager.FleetManager.Asignacionvehiculo.Entity.AsignacionvehiculoEntity;
import ApiFleetManager.FleetManager.Asignacionvehiculo.Repository.AsignacionvehiculoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AsignacionvehiculoService {

    @Autowired
    private AsignacionvehiculoRepository repo;

    public AsignacionvehiculoDTO insertarDatos(@Valid AsignacionvehiculoDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        try{
            AsignacionvehiculoEntity entity = convertirAEntity(jsonData);
            AsignacionvehiculoEntity entitySave = repo.save(entity);
            return convertirADTO(entitySave);
        }catch (Exception e){
            log.error("Error al ingresar la información: " + e.getMessage());
            throw new RuntimeException("Error al registrar el dato");
        }
    }

    private AsignacionvehiculoDTO convertirADTO(AsignacionvehiculoEntity entitySave) {
        AsignacionvehiculoDTO objDTO = new AsignacionvehiculoDTO();
        objDTO.setIdAsignacion(entitySave.getIdAsignacion());
        objDTO.setFkUsuario(entitySave.getFkUsuario());
        objDTO.setFkVehiculo(entitySave.getFkVehiculo());
        objDTO.setFechaasignacion(entitySave.getFechaasignacion());
        objDTO.setHorasalida(entitySave.getHorasalida());
        objDTO.setHoraentrega(entitySave.getHoraentrega());
        objDTO.setKilometrajeinicial(entitySave.getKilometrajeinicial());
        objDTO.setKilometrajefinal(entitySave.getKilometrajefinal());
        objDTO.setObservaciones(entitySave.getObservaciones());
        return objDTO;
    }

    private AsignacionvehiculoEntity convertirAEntity(@Valid AsignacionvehiculoDTO jsonData) {
        AsignacionvehiculoEntity objEntity = new AsignacionvehiculoEntity();
        objEntity.setFkUsuario(jsonData.getFkUsuario());
        objEntity.setFkVehiculo(jsonData.getFkVehiculo());
        objEntity.setFechaasignacion(jsonData.getFechaasignacion());
        objEntity.setHorasalida(jsonData.getHorasalida());
        objEntity.setHoraentrega(jsonData.getHoraentrega());
        objEntity.setKilometrajeinicial(jsonData.getKilometrajeinicial());
        objEntity.setKilometrajefinal(jsonData.getKilometrajefinal());
        objEntity.setObservaciones(jsonData.getObservaciones());
        return objEntity;
    }

    public List<AsignacionvehiculoDTO> listarTodos() {
        List<AsignacionvehiculoEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public AsignacionvehiculoDTO buscarPorId(Integer id) {
        Optional<AsignacionvehiculoEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public AsignacionvehiculoDTO actualizarInfo(Integer id, @Valid AsignacionvehiculoDTO dto) {
        try {
            Optional<AsignacionvehiculoEntity> entidadOpcional = repo.findById(id);
            if (entidadOpcional.isPresent()) {
                AsignacionvehiculoEntity entidad = entidadOpcional.get();
                entidad.setFkUsuario(dto.getFkUsuario());
                entidad.setFkVehiculo(dto.getFkVehiculo());
                entidad.setFechaasignacion(dto.getFechaasignacion());
                entidad.setHorasalida(dto.getHorasalida());
                entidad.setHoraentrega(dto.getHoraentrega());
                entidad.setKilometrajeinicial(dto.getKilometrajeinicial());
                entidad.setKilometrajefinal(dto.getKilometrajefinal());
                entidad.setObservaciones(dto.getObservaciones());
                AsignacionvehiculoEntity datosGuardados = repo.save(entidad);
                return convertirADTO(datosGuardados);
            }
            return null;
        } catch (Exception e) {
            log.error("Ocurrió un error al procesar la información");
            throw new RuntimeException("Error al actualizar: " + e.getMessage());
        }
    }
}
