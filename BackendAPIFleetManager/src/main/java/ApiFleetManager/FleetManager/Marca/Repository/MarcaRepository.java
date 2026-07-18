package ApiFleetManager.FleetManager.Marca.Repository;

import ApiFleetManager.FleetManager.Marca.Entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer> {
    Optional<MarcaEntity> findByNombremarca(String nombremarca);
}
