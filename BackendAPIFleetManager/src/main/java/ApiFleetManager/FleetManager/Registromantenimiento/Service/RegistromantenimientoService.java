package ApiFleetManager.FleetManager.Registromantenimiento.Service;

import ApiFleetManager.FleetManager.Registromantenimiento.DTO.RegistromantenimientoDTO;
import ApiFleetManager.FleetManager.Registromantenimiento.Entity.RegistromantenimientoEntity;
import ApiFleetManager.FleetManager.Registromantenimiento.Repository.RegistromantenimientoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegistromantenimientoService {

    @Autowired
    private RegistromantenimientoRepository repo;

    public RegistromantenimientoDTO insertarDatos(@Valid RegistromantenimientoDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        try{
            RegistromantenimientoEntity entity = convertirAEntity(jsonData);
            RegistromantenimientoEntity entitySave = repo.save(entity);
            return convertirADTO(entitySave);
        }catch (Exception e){
            log.error("Error al ingresar la información: " + e.getMessage());
            throw new RuntimeException("Error al registrar el dato");
        }
    }

    private RegistromantenimientoDTO convertirADTO(RegistromantenimientoEntity entitySave) {
        RegistromantenimientoDTO objDTO = new RegistromantenimientoDTO();
        objDTO.setIdRegistromantenimiento(entitySave.getIdRegistromantenimiento());
        objDTO.setFechaentrada(entitySave.getFechaentrada());
        objDTO.setFechasalida(entitySave.getFechasalida());
        objDTO.setObservaciones(entitySave.getObservaciones());
        objDTO.setFkVehiculo(entitySave.getFkVehiculo());
        objDTO.setFkTaller(entitySave.getFkTaller());
        objDTO.setFkAccidente(entitySave.getFkAccidente());
        return objDTO;
    }

    private RegistromantenimientoEntity convertirAEntity(@Valid RegistromantenimientoDTO jsonData) {
        RegistromantenimientoEntity objEntity = new RegistromantenimientoEntity();
        objEntity.setFechaentrada(jsonData.getFechaentrada());
        objEntity.setFechasalida(jsonData.getFechasalida());
        objEntity.setObservaciones(jsonData.getObservaciones());
        objEntity.setFkVehiculo(jsonData.getFkVehiculo());
        objEntity.setFkTaller(jsonData.getFkTaller());
        objEntity.setFkAccidente(jsonData.getFkAccidente());
        return objEntity;
    }

    public List<RegistromantenimientoDTO> listarTodos() {
        List<RegistromantenimientoEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public RegistromantenimientoDTO buscarPorId(Integer id) {
        Optional<RegistromantenimientoEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public RegistromantenimientoDTO actualizarInfo(Integer id, @Valid RegistromantenimientoDTO dto) {
        try {
            Optional<RegistromantenimientoEntity> entidadOpcional = repo.findById(id);
            if (entidadOpcional.isPresent()) {
                RegistromantenimientoEntity entidad = entidadOpcional.get();
                entidad.setFechaentrada(dto.getFechaentrada());
                entidad.setFechasalida(dto.getFechasalida());
                entidad.setObservaciones(dto.getObservaciones());
                entidad.setFkVehiculo(dto.getFkVehiculo());
                entidad.setFkTaller(dto.getFkTaller());
                entidad.setFkAccidente(dto.getFkAccidente());
                RegistromantenimientoEntity datosGuardados = repo.save(entidad);
                return convertirADTO(datosGuardados);
            }
            return null;
        } catch (Exception e) {
            log.error("Ocurrió un error al procesar la información");
            throw new RuntimeException("Error al actualizar: " + e.getMessage());
        }
    }
}
