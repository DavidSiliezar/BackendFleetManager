package ApiFleetManager.FleetManager.Asignacionvehiculo.Repository;

import ApiFleetManager.FleetManager.Asignacionvehiculo.Entity.AsignacionvehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionvehiculoRepository extends JpaRepository<AsignacionvehiculoEntity, Integer> {
}
