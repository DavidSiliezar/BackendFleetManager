package ApiFleetManager.FleetManager.Taller.Repository;

import ApiFleetManager.FleetManager.Taller.Entity.TallerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TallerRepository extends JpaRepository<TallerEntity, Integer> {
    Optional<TallerEntity> findByNombretaller(String nombretaller);
}
