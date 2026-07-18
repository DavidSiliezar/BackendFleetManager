package ApiFleetManager.FleetManager.Estado.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_ESTADO")
public class EstadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO")
    private Integer idEstado;
    @Column(name = "NOMBREESTADO")
    private String nombreestado;
}
