package ApiFleetManager.FleetManager.Usuario.Service;

import ApiFleetManager.FleetManager.Usuario.DTO.UsuarioDTO;
import ApiFleetManager.FleetManager.Usuario.Entity.UsuarioEntity;
import ApiFleetManager.FleetManager.Usuario.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public UsuarioDTO insertarDatos(@Valid UsuarioDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        UsuarioEntity entity = convertirAEntity(jsonData);
        UsuarioEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    private UsuarioDTO convertirADTO(UsuarioEntity entitySave) {
        UsuarioDTO objDTO = new UsuarioDTO();
        objDTO.setIdUsuario(entitySave.getIdUsuario());
        objDTO.setNombre(entitySave.getNombre());
        objDTO.setApellido(entitySave.getApellido());
        objDTO.setCarnet(entitySave.getCarnet());
        objDTO.setDui(entitySave.getDui());
        objDTO.setTelefono(entitySave.getTelefono());
        objDTO.setCorreo(entitySave.getCorreo());
        objDTO.setPasswordhash(entitySave.getPasswordhash());
        objDTO.setFkRol(entitySave.getFkRol());
        return objDTO;
    }

    private UsuarioEntity convertirAEntity(@Valid UsuarioDTO jsonData) {
        UsuarioEntity objEntity = new UsuarioEntity();
        objEntity.setNombre(jsonData.getNombre());
        objEntity.setApellido(jsonData.getApellido());
        objEntity.setCarnet(jsonData.getCarnet());
        objEntity.setDui(jsonData.getDui());
        objEntity.setTelefono(jsonData.getTelefono());
        objEntity.setCorreo(jsonData.getCorreo());
        objEntity.setPasswordhash(jsonData.getPasswordhash());
        objEntity.setFkRol(jsonData.getFkRol());
        return objEntity;
    }

    public List<UsuarioDTO> listarTodos() {
        List<UsuarioEntity> entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Integer id) {
        Optional<UsuarioEntity> entidadOpcional = repo.findById(id);
        return entidadOpcional.map(this::convertirADTO).orElse(null);
    }

    public List<UsuarioDTO> buscarPorNombre(String nombre) {
        List<UsuarioEntity> entidades = repo.findByNombre(nombre);
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

    public UsuarioDTO actualizarInfo(Integer id, @Valid UsuarioDTO dto) {
        Optional<UsuarioEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()) {
            UsuarioEntity entidad = entidadOpcional.get();
            entidad.setNombre(dto.getNombre());
            entidad.setApellido(dto.getApellido());
            entidad.setCarnet(dto.getCarnet());
            entidad.setDui(dto.getDui());
            entidad.setTelefono(dto.getTelefono());
            entidad.setCorreo(dto.getCorreo());
            entidad.setPasswordhash(dto.getPasswordhash());
            entidad.setFkRol(dto.getFkRol());
            UsuarioEntity datosGuardados = repo.save(entidad);
            return convertirADTO(datosGuardados);
        }
        return null;
    }
}
