package ApiFleetManager.FleetManager.Vehiculo.Service;

import ApiFleetManager.FleetManager.Vehiculo.DTO.VehiculoDTO;
import ApiFleetManager.FleetManager.Vehiculo.Entity.VehiculoEntity;
import ApiFleetManager.FleetManager.Vehiculo.Repository.VehiculoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository repo;

    public VehiculoDTO insertarDatos(@Valid VehiculoDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        try{
            VehiculoEntity entity = convertirAEntity(jsonData);
            VehiculoEntity entitySave = repo.save(entity);
            return convertirADTO(entitySave);
        }catch (Exception e){
            log.error("Error al ingresar la información: " + e.getMessage());
            throw new RuntimeException("Error al registrar el dato");
        }
    }

    private VehiculoDTO convertirADTO(VehiculoEntity entitySave) {
        VehiculoDTO objDTO = new VehiculoDTO();
        objDTO.setIdVehiculo(entitySave.getIdVehiculo());
        objDTO.setPlaca(entitySave.getPlaca());
        objDTO.setKilometraje(entitySave.getKilometraje());
        objDTO.setNumerovin(entitySave.getNumerovin());
        objDTO.setTarjetacirculacion(entitySave.getTarjetacirculacion());
        objDTO.setFkTipo(entitySave.getFkTipo());
        objDTO.setFkEstado(entitySave.getFkEstado());
        return objDTO;
    }

    private VehiculoEntity convertirAEntity(@Valid VehiculoDTO jsonData) {
        VehiculoEntity objEntity = new VehiculoEntity();
        objEntity.setPlaca(jsonData.getPlaca());
        objEntity.setKilometraje(jsonData.getKilometraje());
        objEntity.setNumerovin(jsonData.getNumerovin());
        objEntity.setTarjetacirculacion(jsonData.getTarjetacirculacion());
        objEntity.setFkTipo(jsonData.getFkTipo());
        objEntity.setFkEstado(jsonData.getFkEstado());
        return objEntity;
    }

    public List<VehiculoDTO> listarTodos() {
        List<VehiculoEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public VehiculoDTO buscarPorId(Integer id) {
        Optional<VehiculoEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public VehiculoDTO actualizarInfo(Integer id, @Valid VehiculoDTO dto) {
        try {
            Optional<VehiculoEntity> entidadOpcional = repo.findById(id);
            if (entidadOpcional.isPresent()) {
                VehiculoEntity entidad = entidadOpcional.get();
                entidad.setPlaca(dto.getPlaca());
                entidad.setKilometraje(dto.getKilometraje());
                entidad.setNumerovin(dto.getNumerovin());
                entidad.setTarjetacirculacion(dto.getTarjetacirculacion());
                entidad.setFkTipo(dto.getFkTipo());
                entidad.setFkEstado(dto.getFkEstado());
                VehiculoEntity datosGuardados = repo.save(entidad);
                return convertirADTO(datosGuardados);
            }
            return null;
        } catch (Exception e) {
            log.error("Ocurrió un error al procesar la información");
            throw new RuntimeException("Error al actualizar: " + e.getMessage());
        }
    }
}
