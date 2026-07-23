package ApiFleetManager.FleetManager.Usuario.Repository;

import ApiFleetManager.FleetManager.Usuario.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    List<UsuarioEntity> findByNombre(String nombre);
}
