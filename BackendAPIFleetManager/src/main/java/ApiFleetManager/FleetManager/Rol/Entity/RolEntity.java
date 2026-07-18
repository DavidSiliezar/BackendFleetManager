package ApiFleetManager.FleetManager.Rol.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_ROL")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL")
    private Integer idRol;
    @Column(name = "NOMBREROL")
    private String nombrerol;
}
