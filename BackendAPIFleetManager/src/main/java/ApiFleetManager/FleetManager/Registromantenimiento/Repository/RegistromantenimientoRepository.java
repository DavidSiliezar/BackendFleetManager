package ApiFleetManager.FleetManager.Registromantenimiento.Repository;

import ApiFleetManager.FleetManager.Registromantenimiento.Entity.RegistromantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistromantenimientoRepository extends JpaRepository<RegistromantenimientoEntity, Integer> {
}
