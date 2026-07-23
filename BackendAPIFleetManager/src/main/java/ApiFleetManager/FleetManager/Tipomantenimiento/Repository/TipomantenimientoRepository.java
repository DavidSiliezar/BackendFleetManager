package ApiFleetManager.FleetManager.Tipomantenimiento.Repository;

import ApiFleetManager.FleetManager.Tipomantenimiento.Entity.TipomantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipomantenimientoRepository extends JpaRepository<TipomantenimientoEntity, Integer> {
    List<TipomantenimientoEntity> findByNombretipom(String nombretipom);
}
