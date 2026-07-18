package ApiFleetManager.FleetManager.Tipomantenimiento.Repository;

import ApiFleetManager.FleetManager.Tipomantenimiento.Entity.TipomantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipomantenimientoRepository extends JpaRepository<TipomantenimientoEntity, Integer> {
}
