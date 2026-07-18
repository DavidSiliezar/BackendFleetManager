package ApiFleetManager.FleetManager.Modelo.Repository;

import ApiFleetManager.FleetManager.Modelo.Entity.ModeloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloRepository extends JpaRepository<ModeloEntity, Integer> {
    Optional<ModeloEntity> findByNombremodelo(String nombremodelo);
}
