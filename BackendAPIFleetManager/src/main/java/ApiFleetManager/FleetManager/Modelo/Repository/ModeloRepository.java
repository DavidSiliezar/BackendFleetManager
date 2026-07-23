package ApiFleetManager.FleetManager.Modelo.Repository;

import ApiFleetManager.FleetManager.Modelo.Entity.ModeloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<ModeloEntity, Integer> {
    List<ModeloEntity> findByNombremodelo(String nombremodelo);
}
