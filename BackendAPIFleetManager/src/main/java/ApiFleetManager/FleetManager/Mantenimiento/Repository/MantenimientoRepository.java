package ApiFleetManager.FleetManager.Mantenimiento.Repository;

import ApiFleetManager.FleetManager.Mantenimiento.Entity.MantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MantenimientoRepository extends JpaRepository<MantenimientoEntity, Integer> {
    Optional<MantenimientoEntity> findByNombremantenimiento(String nombremantenimiento);
}
