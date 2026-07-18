package ApiFleetManager.FleetManager.Tipo.Service;

import ApiFleetManager.FleetManager.Tipo.DTO.TipoDTO;
import ApiFleetManager.FleetManager.Tipo.Entity.TipoEntity;
import ApiFleetManager.FleetManager.Tipo.Repository.TipoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TipoService {

    @Autowired
    private TipoRepository repo;

    public TipoDTO insertarDatos(@Valid TipoDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        try{
            TipoEntity entity = convertirAEntity(jsonData);
            TipoEntity entitySave = repo.save(entity);
            return convertirADTO(entitySave);
        }catch (Exception e){
            log.error("Error al ingresar la información: " + e.getMessage());
            throw new RuntimeException("Error al registrar el dato");
        }
    }

    private TipoDTO convertirADTO(TipoEntity entitySave) {
        TipoDTO objDTO = new TipoDTO();
        objDTO.setIdTipo(entitySave.getIdTipo());
        objDTO.setNombretipo(entitySave.getNombretipo());
        objDTO.setFkModelo(entitySave.getFkModelo());
        return objDTO;
    }

    private TipoEntity convertirAEntity(@Valid TipoDTO jsonData) {
        TipoEntity objEntity = new TipoEntity();
        objEntity.setNombretipo(jsonData.getNombretipo());
        objEntity.setFkModelo(jsonData.getFkModelo());
        return objEntity;
    }

    public List<TipoDTO> listarTodos() {
        List<TipoEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public TipoDTO buscarPorId(Integer id) {
        Optional<TipoEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public TipoDTO buscarPorNombre(String nombre) {
        Optional<TipoEntity> entidadOpcional = repo.findByNombretipo(nombre);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public TipoDTO actualizarInfo(Integer id, @Valid TipoDTO dto) {
        try {
            Optional<TipoEntity> entidadOpcional = repo.findById(id);
            if (entidadOpcional.isPresent()) {
                TipoEntity entidad = entidadOpcional.get();
                entidad.setNombretipo(dto.getNombretipo());
                entidad.setFkModelo(dto.getFkModelo());
                TipoEntity datosGuardados = repo.save(entidad);
                return convertirADTO(datosGuardados);
            }
            return null;
        } catch (Exception e) {
            log.error("Ocurrió un error al procesar la información");
            throw new RuntimeException("Error al actualizar: " + e.getMessage());
        }
    }
}
