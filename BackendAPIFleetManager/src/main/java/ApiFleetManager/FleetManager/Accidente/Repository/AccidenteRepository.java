package ApiFleetManager.FleetManager.Accidente.Repository;

import ApiFleetManager.FleetManager.Accidente.Entity.AccidenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidenteRepository extends JpaRepository<AccidenteEntity, Integer> {
}
