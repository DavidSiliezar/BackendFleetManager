package ApiFleetManager.FleetManager.Vehiculo.Repository;

import ApiFleetManager.FleetManager.Vehiculo.Entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Integer> {
    Optional<VehiculoEntity> findByPlaca(String placa);
}
