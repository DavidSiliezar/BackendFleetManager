package ApiFleetManager.FleetManager.Tipo.Repository;

import ApiFleetManager.FleetManager.Tipo.Entity.TipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoRepository extends JpaRepository<TipoEntity, Integer> {
    Optional<TipoEntity> findByNombretipo(String nombretipo);
}
