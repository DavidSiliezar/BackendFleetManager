package ApiFleetManager.FleetManager.Modelo.Service;

import ApiFleetManager.FleetManager.Modelo.DTO.ModeloDTO;
import ApiFleetManager.FleetManager.Modelo.Entity.ModeloEntity;
import ApiFleetManager.FleetManager.Modelo.Repository.ModeloRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ModeloService {

    @Autowired
    private ModeloRepository repo;

    public ModeloDTO insertarDatos(@Valid ModeloDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        ModeloEntity entity = convertirAEntity(jsonData);
        ModeloEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private ModeloDTO convertirADTO(ModeloEntity entitySave) {
        ModeloDTO objDTO = new ModeloDTO();
        objDTO.setIdModelo(entitySave.getIdModelo());
        objDTO.setNombremodelo(entitySave.getNombremodelo());
        objDTO.setFkMarca(entitySave.getFkMarca());
        return objDTO;
    }

    private ModeloEntity convertirAEntity(@Valid ModeloDTO jsonData) {
        ModeloEntity objEntity = new ModeloEntity();
        objEntity.setNombremodelo(jsonData.getNombremodelo());
        objEntity.setFkMarca(jsonData.getFkMarca());
        return objEntity;
    }

    public List<ModeloDTO> listarTodos() {
        List<ModeloEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ModeloDTO buscarPorId(Integer id) {
        Optional<ModeloEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public ModeloDTO buscarPorNombre(String nombre) {
        Optional<ModeloEntity> entidadOpcional = repo.findByNombremodelo(nombre);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public ModeloDTO actualizarInfo(Integer id, @Valid ModeloDTO dto) {
        Optional<ModeloEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            ModeloEntity entidad = entidadOpcional.get();
            entidad.setNombremodelo(dto.getNombremodelo());
            entidad.setFkMarca(dto.getFkMarca());
            ModeloEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
