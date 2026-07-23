package ApiFleetManager.FleetManager.Taller.Service;

import ApiFleetManager.FleetManager.Taller.DTO.TallerDTO;
import ApiFleetManager.FleetManager.Taller.Entity.TallerEntity;
import ApiFleetManager.FleetManager.Taller.Repository.TallerRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TallerService {

    @Autowired
    private TallerRepository repo;

    public TallerDTO insertarDatos(@Valid TallerDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        TallerEntity entity = convertirAEntity(jsonData);
        TallerEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private TallerDTO convertirADTO(TallerEntity entitySave) {
        TallerDTO objDTO = new TallerDTO();
        objDTO.setIdTaller(entitySave.getIdTaller());
        objDTO.setNombretaller(entitySave.getNombretaller());
        objDTO.setDireccion(entitySave.getDireccion());
        objDTO.setResponsable(entitySave.getResponsable());
        objDTO.setTelefonotaller(entitySave.getTelefonotaller());
        return objDTO;
    }

    private TallerEntity convertirAEntity(@Valid TallerDTO jsonData) {
        TallerEntity objEntity = new TallerEntity();
        objEntity.setNombretaller(jsonData.getNombretaller());
        objEntity.setDireccion(jsonData.getDireccion());
        objEntity.setResponsable(jsonData.getResponsable());
        objEntity.setTelefonotaller(jsonData.getTelefonotaller());
        return objEntity;
    }

    public List<TallerDTO> listarTodos() {
        List<TallerEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public TallerDTO buscarPorId(Integer id) {
        Optional<TallerEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public List<TallerDTO> buscarPorNombre(String nombre) {
        List<TallerEntity> entidades = repo.findByNombretaller(nombre);
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

    public TallerDTO actualizarInfo(Integer id, @Valid TallerDTO dto) {
        Optional<TallerEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            TallerEntity entidad = entidadOpcional.get();
            entidad.setNombretaller(dto.getNombretaller());
            entidad.setDireccion(dto.getDireccion());
            entidad.setResponsable(dto.getResponsable());
            entidad.setTelefonotaller(dto.getTelefonotaller());
            TallerEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
