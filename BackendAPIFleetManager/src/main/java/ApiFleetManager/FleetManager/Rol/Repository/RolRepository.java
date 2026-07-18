package ApiFleetManager.FleetManager.Rol.Repository;

import ApiFleetManager.FleetManager.Rol.Entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    Optional<RolEntity> findByNombrerol(String nombrerol);
}
