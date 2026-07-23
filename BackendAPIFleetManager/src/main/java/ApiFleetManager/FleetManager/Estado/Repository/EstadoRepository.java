package ApiFleetManager.FleetManager.Estado.Repository;

import ApiFleetManager.FleetManager.Estado.Entity.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Integer> {
    List<EstadoEntity> findByNombreestado(String nombreestado);
}
