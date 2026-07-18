package ApiFleetManager.FleetManager.Accidente.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_ACCIDENTE")
public class AccidenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACCIDENTE")
    private Integer idAccidente;
    @Column(name = "FECHAACCIDENTE")
    private java.time.LocalDate fechaaccidente;
    @Column(name = "LUGAR")
    private String lugar;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "IMAGEN")
    private String imagen;
    @Column(name = "FK_ASIGNACION")
    private Integer fkAsignacion;
}
