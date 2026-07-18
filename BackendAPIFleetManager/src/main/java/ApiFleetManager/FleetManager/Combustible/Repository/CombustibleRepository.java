package ApiFleetManager.FleetManager.Combustible.Repository;

import ApiFleetManager.FleetManager.Combustible.Entity.CombustibleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombustibleRepository extends JpaRepository<CombustibleEntity, Integer> {
}
