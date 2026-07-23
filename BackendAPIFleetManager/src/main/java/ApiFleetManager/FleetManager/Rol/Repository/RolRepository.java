package ApiFleetManager.FleetManager.Rol.Repository;

import ApiFleetManager.FleetManager.Rol.Entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    List<RolEntity> findByNombrerol(String nombrerol);
}
