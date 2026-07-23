package ApiFleetManager.FleetManager.Combustible.Service;

import ApiFleetManager.FleetManager.Combustible.DTO.CombustibleDTO;
import ApiFleetManager.FleetManager.Combustible.Entity.CombustibleEntity;
import ApiFleetManager.FleetManager.Combustible.Repository.CombustibleRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CombustibleService {

    @Autowired
    private CombustibleRepository repo;

    public CombustibleDTO insertarDatos(@Valid CombustibleDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        CombustibleEntity entity = convertirAEntity(jsonData);
        CombustibleEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private CombustibleDTO convertirADTO(CombustibleEntity entitySave) {
        CombustibleDTO objDTO = new CombustibleDTO();
        objDTO.setIdCombustible(entitySave.getIdCombustible());
        objDTO.setGalonestotales(entitySave.getGalonestotales());
        objDTO.setFechacarga(entitySave.getFechacarga());
        objDTO.setCosto(entitySave.getCosto());
        objDTO.setImagenfactura(entitySave.getImagenfactura());
        objDTO.setFkAsignacion(entitySave.getFkAsignacion());
        return objDTO;
    }

    private CombustibleEntity convertirAEntity(@Valid CombustibleDTO jsonData) {
        CombustibleEntity objEntity = new CombustibleEntity();
        objEntity.setGalonestotales(jsonData.getGalonestotales());
        objEntity.setFechacarga(jsonData.getFechacarga());
        objEntity.setCosto(jsonData.getCosto());
        objEntity.setImagenfactura(jsonData.getImagenfactura());
        objEntity.setFkAsignacion(jsonData.getFkAsignacion());
        return objEntity;
    }

    public List<CombustibleDTO> listarTodos() {
        List<CombustibleEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public CombustibleDTO buscarPorId(Integer id) {
        Optional<CombustibleEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public CombustibleDTO actualizarInfo(Integer id, @Valid CombustibleDTO dto) {
        Optional<CombustibleEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            CombustibleEntity entidad = entidadOpcional.get();
            entidad.setGalonestotales(dto.getGalonestotales());
            entidad.setFechacarga(dto.getFechacarga());
            entidad.setCosto(dto.getCosto());
            entidad.setImagenfactura(dto.getImagenfactura());
            entidad.setFkAsignacion(dto.getFkAsignacion());
            CombustibleEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
