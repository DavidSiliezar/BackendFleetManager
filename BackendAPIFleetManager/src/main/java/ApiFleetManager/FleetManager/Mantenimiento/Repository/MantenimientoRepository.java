package ApiFleetManager.FleetManager.Mantenimiento.Repository;

import ApiFleetManager.FleetManager.Mantenimiento.Entity.MantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends JpaRepository<MantenimientoEntity, Integer> {
}
