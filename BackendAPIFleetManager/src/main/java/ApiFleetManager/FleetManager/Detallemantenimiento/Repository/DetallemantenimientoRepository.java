package ApiFleetManager.FleetManager.Detallemantenimiento.Repository;

import ApiFleetManager.FleetManager.Detallemantenimiento.Entity.DetallemantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallemantenimientoRepository extends JpaRepository<DetallemantenimientoEntity, Integer> {
}
