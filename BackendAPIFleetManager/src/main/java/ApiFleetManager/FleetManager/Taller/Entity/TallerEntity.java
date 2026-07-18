package ApiFleetManager.FleetManager.Taller.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_TALLER")
public class TallerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TALLER")
    private Integer idTaller;
    @Column(name = "NOMBRETALLER")
    private String nombretaller;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "RESPONSABLE")
    private String responsable;
    @Column(name = "TELEFONOTALLER")
    private String telefonotaller;
}
