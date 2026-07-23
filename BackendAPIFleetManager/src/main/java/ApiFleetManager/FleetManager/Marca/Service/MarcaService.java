package ApiFleetManager.FleetManager.Marca.Service;

import ApiFleetManager.FleetManager.Marca.DTO.MarcaDTO;
import ApiFleetManager.FleetManager.Marca.Entity.MarcaEntity;
import ApiFleetManager.FleetManager.Marca.Repository.MarcaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repo;

    public MarcaDTO insertarDatos(@Valid MarcaDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        MarcaEntity entity = convertirAEntity(jsonData);
        MarcaEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private MarcaDTO convertirADTO(MarcaEntity entitySave) {
        MarcaDTO objDTO = new MarcaDTO();
        objDTO.setIdMarca(entitySave.getIdMarca());
        objDTO.setNombremarca(entitySave.getNombremarca());
        return objDTO;
    }

    private MarcaEntity convertirAEntity(@Valid MarcaDTO jsonData) {
        MarcaEntity objEntity = new MarcaEntity();
        objEntity.setNombremarca(jsonData.getNombremarca());
        return objEntity;
    }

    public List<MarcaDTO> listarTodos() {
        List<MarcaEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MarcaDTO buscarPorId(Integer id) {
        Optional<MarcaEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public MarcaDTO buscarPorNombre(String nombre) {
        Optional<MarcaEntity> entidadOpcional = repo.findByNombremarca(nombre);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public boolean eliminarInfo(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public MarcaDTO actualizarInfo(Integer id, @Valid MarcaDTO dto) {
        Optional<MarcaEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            MarcaEntity entidad = entidadOpcional.get();
            entidad.setNombremarca(dto.getNombremarca());
            MarcaEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
